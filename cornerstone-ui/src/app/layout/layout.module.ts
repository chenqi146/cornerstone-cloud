import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthLayoutComponent } from './auth-layout/auth-layout.component';
import { AppRoutingModule } from '../app-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
    declarations: [AuthLayoutComponent],
    imports: [CommonModule, AppRoutingModule, FlexLayoutModule]
})
export class LayoutModule {}
