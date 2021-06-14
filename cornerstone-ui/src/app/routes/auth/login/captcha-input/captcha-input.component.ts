import { FocusMonitor } from '@angular/cdk/a11y';
import { coerceBooleanProperty } from '@angular/cdk/coercion';
import {
	Component,
	ElementRef,
	HostBinding,
	Inject,
	Input,
	OnDestroy,
	Optional,
	Self,
	ViewChild,
} from '@angular/core';
import {
	ControlValueAccessor,
	FormBuilder,
	FormGroup,
	NgControl,
	Validators,
} from '@angular/forms';
import {
	MatFormField,
	MatFormFieldControl,
	MAT_FORM_FIELD,
} from '@angular/material/form-field';
import { Subject } from 'rxjs';
import { AuthService, CaptchaVo } from '../../../../service/auth/auth.service';

@Component({
	selector: 'app-captcha-input',
	templateUrl: 'captcha-input.component.html',
	styleUrls: ['./captcha-input.component.scss'],
	providers: [
		{ provide: MatFormFieldControl, useExisting: CaptchaInputComponent },
	],
	host: {
		'[class.captcha-input-element]': 'shouldLabelFloat',
		'[id]': 'id',
	},
})
export class CaptchaInputComponent
	implements MatFormFieldControl<CaptchaVo>, OnDestroy, ControlValueAccessor
{
	public get captchaVoField(): CaptchaVo | null {
		return this._captchaVoField;
	}

	public get value(): CaptchaVo | null {
		if (this.formGroup.valid && this._captchaVoField) {
			this._captchaVoField.code = this.formGroup.get('captchaCode')?.value;
			return new CaptchaVo(
				this._captchaVoField.uuid,
				'',
				this._captchaVoField.code
			);
		}
		return null;
	}

	public set value(val: CaptchaVo | null) {
		const v = val || this._captchaVoField;
		if (v) {
			this.formGroup.setValue(v);
		}
		this.stateChanges.next();
	}

	@Input()
	public get placeholder(): string {
		return this._placeholder;
	}

	public set placeholder(plh) {
		this._placeholder = plh;
		this.stateChanges.next();
	}

	public get empty(): boolean {
		const {
			value: { captchaCodeInput },
		} = this.formGroup;
		return !captchaCodeInput;
	}

	public get shouldLabelFloat(): boolean {
		return this.focused || !this.empty;
	}

	@Input()
	public get required(): boolean {
		return this._required;
	}

	public set required(value: boolean) {
		this._required = coerceBooleanProperty(value);
		this.stateChanges.next();
	}

	@Input()
	public get disabled(): boolean {
		return this._disabled;
	}

	public set disabled(value: boolean) {
		this._disabled = coerceBooleanProperty(value);
		this._disabled ? this.formGroup.disable() : this.formGroup.enable();
		this.stateChanges.next();
	}

	public get errorState(): boolean {
		return this.formGroup.invalid && this.formGroup.dirty;
	}
	public static nextId = 0;
	@HostBinding('captcha-input-element')
	public id = `app-captcha-input-${CaptchaInputComponent.nextId++}`;

	@ViewChild('captchaCode') public captchaCodeInput!: HTMLInputElement;
	public formGroup: FormGroup;
	public stateChanges = new Subject<void>();
	public controlType = 'app-captcha-input';
	public focused = false;

	@Input('aria-describedby') public userAriaDescribedBy: string | undefined;
	private _captchaVoField: CaptchaVo | null = null;
	private _placeholder: string = '' as string;
	private _required = false;
	private _disabled = false;

	public constructor(
		fb: FormBuilder,
		private focusMonitor: FocusMonitor,
		@Optional() @Self() public ngControl: NgControl,
		@Optional() @Inject(MAT_FORM_FIELD) public formField: MatFormField,
		public authService: AuthService,
		private elRef: ElementRef<HTMLElement>
	) {
		this.formGroup = fb.group({
			captchaCode: [
				null,
				[Validators.required, Validators.minLength(4), Validators.maxLength(4)],
			],
		});
		if (ngControl !== null) {
			ngControl.valueAccessor = this;
		}
		focusMonitor.monitor(elRef, true).subscribe((origin) => {
			if (this.focused && !origin) {
				this.onTouched();
			}
			this.focused = !!origin;
			this.stateChanges.next();
		});
		authService
			.getCaptcha()
			.subscribe((res) => (this._captchaVoField = res.data));
	}

	public onChange = (_: any) => {};
	public onTouched = () => {};

	public setDescribedByIds(ids: string[]): void {
		const controlElement =
			this.elRef.nativeElement.querySelector('.captcha-container')!;
		controlElement.setAttribute('aria-describedby', ids.join(' '));
	}

	public onContainerClick(event: MouseEvent): void {
		if (this.formGroup.controls.captchaCode.valid) {
			this.focusMonitor.focusVia(this.captchaCodeInput, 'program');
		}
	}

	public ngOnDestroy(): void {
		this.stateChanges.complete();
		this.focusMonitor.stopMonitoring(this.elRef);
	}

	public writeValue(value: string): void {
		console.log(value);

		if (value) {
			if (this.value !== null) {
				this.value.code = value;
			}
			this.onChange(value);
		}
	}

	public registerOnChange(fn: any): void {
		this.onChange = fn;
	}

	public registerOnTouched(fn: any): void {
		this.onTouched = fn;
	}

	public setDisabledState(isDisabled: boolean): void {
		this.disabled = isDisabled;
	}

	public _input() {
		this.onChange(this.value);
	}

	/**
	 * 刷新验证码
	 */
	public refreshCaptcha(): void {
		this.authService.getCaptcha().subscribe((res) => {
			this._captchaVoField = res.data;
			this.onChange(this.value);
		});
	}
}
