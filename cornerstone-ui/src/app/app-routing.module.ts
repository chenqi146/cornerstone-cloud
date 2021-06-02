import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthLayoutComponent } from './layout/auth-layout/auth-layout.component';
import { LoginComponent } from './routes/auth/login/login.component';

const routes: Routes = [{
  path: 'auth',
  component: AuthLayoutComponent,
  children: [
    {
      path: 'login',
      component: LoginComponent
    }
  ]

}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
