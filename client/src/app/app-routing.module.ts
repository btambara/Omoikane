import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { AboutComponent } from './components/about/about.component';
import { ResumeComponent } from './components/resume/resume.component';
import { EditComponent } from './components/edit/edit.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    runGuardsAndResolvers: 'always',
  },
  {
    path: 'about',
    component: AboutComponent,
    runGuardsAndResolvers: 'always',
  },
  {
    path: 'resume',
    component: ResumeComponent,
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'edit',
    component: EditComponent,
    runGuardsAndResolvers: 'always',
    canActivate: [AuthGuardService]
  },
  {
    path: '**',
    redirectTo: 'about'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
