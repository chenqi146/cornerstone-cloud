import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReturnForm } from '../../common/model/BaseModel';

@Injectable({
	providedIn: 'root',
})
export class AuthService {
	public constructor(private http: HttpClient) {}

	public getCaptcha(): Observable<ReturnForm<CaptchaVo>> {
		return this.http.get<ReturnForm<CaptchaVo>>(
			'http://localhost:8080/cornerstone/captcha/getVerificationImage'
		);
	}
}

/**
 * 分页响应
 */
export class CaptchaVo {
	public _uuid: string;
	public _imgBase64: string;
	public _code: string;

	public constructor(_uuid: string, _imgBase64: string, _code: string) {
		this._uuid = _uuid;
		this._imgBase64 = _imgBase64;
		this._code = _code;
	}

	public get uuid(): string {
		return this._uuid;
	}

	public set uuid(value: string) {
		this._uuid = value;
	}

	public get imgBase64(): string {
		return this._imgBase64;
	}

	public set imgBase64(value: string) {
		this._imgBase64 = value;
	}

	public get code(): string {
		return this._code;
	}

	public set code(value: string) {
		this._code = value;
	}
}
