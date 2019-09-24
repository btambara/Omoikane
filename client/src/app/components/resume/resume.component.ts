import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { JobService } from 'src/app/services/job.service';
import { ProjectService } from 'src/app/services/project.service';
import { EducationService } from 'src/app/services/education.service';
import { Contact } from 'src/app/models/contact';
import { Project } from 'src/app/models/project';
import { Job } from 'src/app/models/job';
import { Education } from 'src/app/models/education';
import { StatementService } from 'src/app/services/statement.service';
import { Statement } from 'src/app/models/statement';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {

  constructor(
    private contactService: ContactService,
    private jobService: JobService,
    private projectService: ProjectService,
    private educationService: EducationService,
    private statementService: StatementService,
    private datePipe: DatePipe,
  ) { }

  contact: Contact;
  statement: Statement;
  jobs: Job[];
  projects: Project[];
  education: Education[];

  ngOnInit() {
    this.contactService.getContactInformation().subscribe(
      result => {
        this.contact = result;
        console.log(this.contact);
      },
      error => { console.log(error); }
    );

    this.statementService.getStatementInformation().subscribe(
      result => {
        this.statement = result;
        console.log(this.statement);
      },
      error => { console.log(error); }
    );

    this.jobService.getAllJobsInformation().subscribe(
      result => {
        if (result._embedded) {
          this.jobs = result._embedded.jobList;
          this.jobs.sort(function (job1, job2) {
            let d1 = new Date(job1.ended);
            let d2 = new Date(job2.ended);

            return d1.getDate() - d2.getDate();
          });
          console.log(this.jobs);
        }
      },
      error => { console.log(error); }
    );

    this.projectService.getAllProjectsInformation().subscribe(
      result => {
        if (result._embedded) {
          this.projects = result._embedded.projectList;
          this.projects.sort(function (project1, project2) {
            let p1 = new Date(project1.projectEnd);
            let p2 = new Date(project2.projectEnd);

            return p1.getDate() - p2.getDate();
          });
          console.log(this.projects);
        }
      },
      error => { console.log(error); }
    );

    this.educationService.getAllEducationInformation().subscribe(
      result => {
        if (result._embedded) {
          this.education = result._embedded.educationList;
          this.education.sort(function (education1, education2) {
            let e1 = new Date(education1.ended);
            let e2 = new Date(education2.ended);

            return e1.getDate() - e2.getDate();
          });
          console.log(this.education);
        }
      },
      error => { console.log(error); }
    );
  }

  transformDate(date: any): string {
    return this.datePipe.transform(date, "yyyy");
  }
}
