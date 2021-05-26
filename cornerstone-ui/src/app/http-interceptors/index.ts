import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ResponseHandlerInterceptor } from './response-handler.interceptor';
// 拦截器数组
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: ResponseHandlerInterceptor, multi: true }
];
