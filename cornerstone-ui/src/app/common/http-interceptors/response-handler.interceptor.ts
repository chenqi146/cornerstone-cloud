import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, filter, map } from 'rxjs/operators';

@Injectable()
export class ResponseHandlerInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      // @ts-ignore
      filter(event => event instanceof HttpResponse && event.status === 200),
      map((event: HttpResponse<any>) => {
        console.log('响应消息: ', event.body);

        switch (event.body.status) {
          case 200: {
            return event.clone({ body: event.body });
            break;
          }
          default:
            return event.clone({});
        }
      }),
      catchError(err => {
        // 统一处理错误信息，可以使用项目已有的消息组件抛出错误信息，也可以根据请求的错误码类型做更多的处理，
        console.log(err, '后端接口报错');
        throw err;
      })
    );
  }
}
