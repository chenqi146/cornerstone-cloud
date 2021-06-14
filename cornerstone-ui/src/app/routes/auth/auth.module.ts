import { NgModule } from '@angular/core';
import { MaterialModule } from '../../material.module';
import { AuthRoutingModule } from './auth-routing.module';
import { CaptchaInputComponent } from './login/captcha-input/captcha-input.component';
import { LoginComponent } from './login/login.component';

@NgModule({
	declarations: [LoginComponent, CaptchaInputComponent],
	imports: [MaterialModule, AuthRoutingModule],
})
export class AuthModule {}
