import { TestBed } from '@angular/core/testing';

import { ResponseHandlerInterceptor } from './response-handler.interceptor';

describe('ResponseHandlerInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ResponseHandlerInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ResponseHandlerInterceptor = TestBed.inject(ResponseHandlerInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
