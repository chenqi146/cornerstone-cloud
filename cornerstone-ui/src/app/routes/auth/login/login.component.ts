import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService, CaptchaVo } from '../../../service/auth/auth.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  captchaVo: CaptchaVo | undefined;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService) {
    this.loginForm = this.formBuilder.group( {
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      captchaCode: ['', [Validators.required]],
    });
    this.authService.getCaptcha().subscribe(res => {
      this.captchaVo = res.data;

      console.log(res.data);
      console.log(this.captchaVo);
    });
  }

  ngOnInit(): void {
  }

  get username(): AbstractControl | null {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl | null {
    return this.loginForm.get('password');
  }

  get captchaCode(): AbstractControl | null {
    return this.loginForm.get('captchaCode');
  }

  login(): void {
    console.log('s');
  }

}
