import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReturnForm } from '../common/model/BaseModel';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  url = 'http://localhost:8080/cornerstone/captcha/getVerificationImage';

  constructor(private http: HttpClient) {
  }

  public getData(): Observable<ReturnForm<any>> {
    return this.http.get<ReturnForm<any>>(this.url);
  }
}
