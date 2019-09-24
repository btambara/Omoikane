import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {
  MatToolbarModule, MatIconModule, MatButtonModule, MatInputModule, MatDialogModule,
  MatSidenavModule, MatListModule, MatDividerModule, MatTableModule,
  MatMenuModule, MatCardModule, MatGridListModule, MatProgressSpinnerModule,
} from '@angular/material';
import {MatChipsModule} from '@angular/material/chips';
import {
  MatFormFieldModule
} from '@angular/material/form-field';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { ResumeComponent } from './components/resume/resume.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { EditComponent } from './components/edit/edit.component';
import { JobDialogComponent } from './components/job-dialog/job-dialog.component';
import { ProjectDialogComponent } from './components/project-dialog/project-dialog.component';
import { EducationDialogComponent } from './components/education-dialog/education-dialog.component';
import { JobService } from './services/job.service';
import { ProjectService } from './services/project.service';
import { EducationService } from './services/education.service';
import {DatePipe} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AboutComponent,
    ResumeComponent,
    LoginComponent,
    EditComponent,
    JobDialogComponent,
    ProjectDialogComponent,
    EducationDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatChipsModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatListModule,
    MatCardModule,
    MatGridListModule,
    MatDividerModule,
    MatSidenavModule,
    FlexLayoutModule
  ],
  entryComponents: [JobDialogComponent, ProjectDialogComponent, EducationDialogComponent],
  providers: [JobService, ProjectService, EducationService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
