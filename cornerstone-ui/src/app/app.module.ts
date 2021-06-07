import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';

import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpInterceptorProviders } from './common/http-interceptors';
import { LayoutModule } from './layout/layout.module';
import { AuthRoutingModule } from './routes/auth/auth-routing.module';
import { RouterModule } from '@angular/router';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { LoginComponent } from './routes/auth/login/login.component';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';


// Required for AOT compilation
export function TranslateHttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


@NgModule({
  declarations: [
    AppComponent, LoginComponent
  ],
  imports: [
    LayoutModule,
    AuthRoutingModule,
    FlexLayoutModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    MatSliderModule,
    BrowserAnimationsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: TranslateHttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    MatCardModule,
    ReactiveFormsModule,
    MaterialModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: HttpInterceptorProviders,
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private translate: TranslateService) {
    // this language will be used as a fallback when a translation isn't found in the current language
    this.translate.setDefaultLang('zh-CN');

    // the language to use, if the language isn't available, it will use the current loader to get them
    this.translate.use('zh-CN');
  }
}
