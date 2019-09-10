import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { AboutComponent } from './components/about/about.component';
import { ResumeComponent } from './components/resume/resume.component';


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
