import { Component, OnInit } from '@angular/core';
import {
	AbstractControl,
	FormBuilder,
	FormControl,
	FormGroup,
	Validators,
} from '@angular/forms';
import { AuthService, CaptchaVo } from '../../../service/auth/auth.service';
import { ConsoleLogger, Logger } from '@angular/compiler-cli/ngcc';
import { NGXLogger } from 'ngx-logger';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
	public loginForm: FormGroup;

	public constructor(
		private formBuilder: FormBuilder,
		private authService: AuthService,
		private logger: NGXLogger
	) {
		this.loginForm = this.formBuilder.group({
			username: ['', [Validators.required]],
			password: ['', [Validators.required]],
			captcha: new FormControl(''),
		});
	}

	public ngOnInit(): void {}

	public get username(): AbstractControl | null {
		return this.loginForm.get('username');
	}

	public get password(): AbstractControl | null {
		return this.loginForm.get('password');
	}

	public login(): void {
		// todo 登录
	}
}
