import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaptchaInputComponent } from './captcha-input.component';

describe('CaptchaInputComponent', () => {
  let component: CaptchaInputComponent;
  let fixture: ComponentFixture<CaptchaInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaptchaInputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaptchaInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
