import { Component } from '@angular/core';
import { AbstractControl, ControlValueAccessor } from '@angular/forms';
import { CaptchaVo } from '../../../../service/auth/auth.service';

@Component({
  selector: 'app-captcha-input',
  templateUrl: 'captcha-input.component.html',
  styleUrls: ['./captcha-input.component.scss']
})
export class CaptchaInputComponent implements ControlValueAccessor  {

  innerCaptchaCode: AbstractControl | null;
  captchaVo: CaptchaVo;

  constructor() {
    this.innerCaptchaCode = null;
    this.captchaVo = {} as CaptchaVo;
  }

  get captcha(): CaptchaVo {
    return this.captchaVo;
  }

  set captcha(captcha) {
    this.captchaVo = captcha;
  }

  get captchaCode(): AbstractControl | null {
    return this.innerCaptchaCode;
  }

  set captchaCode(value) {
    if (value) {
      this.innerCaptchaCode = value;
    }
  }

  registerOnChange(fn: AbstractControl | null): void {
    this.innerCaptchaCode = fn;
  }

  registerOnTouched(fn: AbstractControl | null): void {
  }

  writeValue(obj: AbstractControl | null): void {
    if (obj) {
      this.innerCaptchaCode = obj;
    }
  }


}
