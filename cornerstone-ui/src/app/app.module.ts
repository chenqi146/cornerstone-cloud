import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';

import { HttpClientModule } from '@angular/common/http';
import { HttpInterceptorProviders } from './common/http-interceptors';
import { LayoutModule } from './layout/layout.module';
import { AuthRoutingModule } from './routes/auth/auth-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    LayoutModule,
    AuthRoutingModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    MatSliderModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: HttpInterceptorProviders,
  bootstrap: [AppComponent]
})
export class AppModule { }
