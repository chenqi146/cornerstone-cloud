import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReturnForm } from '../../common/model/BaseModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getCaptcha(): Observable<ReturnForm<CaptchaVo>>{
    return this.http.get<ReturnForm<CaptchaVo>>('http://localhost:8080/cornerstone/captcha/getVerificationImage');
  }
}



/**
 * 分页响应
 */
export interface CaptchaVo {
  uuid: string;
  imgBase64: string;

}
