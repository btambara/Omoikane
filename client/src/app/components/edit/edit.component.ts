import { Component, OnInit, ViewChild } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Contact } from 'src/app/models/contact';
import { CoverletterService } from 'src/app/services/coverletter.service';
import { CoverLetter } from 'src/app/models/coverletter';
import { Job } from 'src/app/models/job';
import { Project } from 'src/app/models/project';
import { MatDialog } from '@angular/material/dialog';
import { JobDialogComponent } from '../job-dialog/job-dialog.component';
import { EducationDialogComponent } from '../education-dialog/education-dialog.component';
import { ProjectDialogComponent } from '../project-dialog/project-dialog.component';
import { MatTableDataSource, MatTable } from '@angular/material';
import { JobService } from 'src/app/services/job.service';
import { ProjectService } from 'src/app/services/project.service';
import { EducationService } from 'src/app/services/education.service';
import { Education } from 'src/app/models/education';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  @ViewChild('jobsTable', { static: false }) jobTable: MatTable<any>;
  @ViewChild('projectsTable', { static: false }) projectTable: MatTable<any>;
  @ViewChild('educationTB', { static: false }) educationTable: MatTable<any>;

  contactForm = new FormGroup({
    firstNameField: new FormControl([Validators.required, Validators.minLength(1)]),
    lastNameField: new FormControl([Validators.required, Validators.minLength(1)]),
    messageField: new FormControl([Validators.required, Validators.minLength(1)]),
    emailField: new FormControl([Validators.required, Validators.minLength(1)]),
    githubAddressField: new FormControl([Validators.required, Validators.minLength(1)]),
    linkedinAddressField: new FormControl([Validators.required, Validators.minLength(1)]),
  });

  coverLetterForm = new FormGroup({
    headlineField: new FormControl([Validators.required, Validators.minLength(1)]),
    coverLetterField: new FormControl([Validators.required, Validators.minLength(1)]),
  });

  resumeForm = new FormGroup({
    statementField: new FormControl([Validators.required, Validators.minLength(1)]),
  });

  contact: Contact;
  coverLetter: CoverLetter;

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private educationService: EducationService,
    private projectService: ProjectService,
    private contactService: ContactService,
    private coverLetterService: CoverletterService,
    private jobService: JobService
  ) { }

  ngOnInit() {
    this.initializeContact();

    this.initializeCoverLetter();

    this.initializeResume();
  }

  initializeContact() {
    this.contactService.getContactInformation()
      .subscribe(
        data => {
          this.contact = {
            "cid": 10,
            "firstName": data.firstName,
            "lastName": data.lastName,
            "message": data.message,
            "email": data.email,
            "githubAddress": data.githubAddress,
            "linkedinAddress": data.linkedinAddress
          };

          if (this.contact) {
            this.contactForm.get('firstNameField').setValue(this.contact.firstName);
            this.contactForm.get('lastNameField').setValue(this.contact.lastName);
            this.contactForm.get('messageField').setValue(this.contact.message);
            this.contactForm.get('emailField').setValue(this.contact.email);
            this.contactForm.get('githubAddressField').setValue(this.contact.githubAddress);
            this.contactForm.get('linkedinAddressField').setValue(this.contact.linkedinAddress);
          }
        },
        error => {
          this.contact = {
            "cid": 10,
            "firstName": "FIRST NAME",
            "lastName": "LAST NAME",
            "message": "STATUS",
            "email": "email@email.com",
            "githubAddress": "www.github.com",
            "linkedinAddress": "www.linkedin.com"
          };

          if (this.contact) {
            this.contactForm.get('firstNameField').setValue(this.contact.firstName);
            this.contactForm.get('lastNameField').setValue(this.contact.lastName);
            this.contactForm.get('messageField').setValue(this.contact.message);
            this.contactForm.get('emailField').setValue(this.contact.email);
            this.contactForm.get('githubAddressField').setValue(this.contact.githubAddress);
            this.contactForm.get('linkedinAddressField').setValue(this.contact.linkedinAddress);

            this.contactService.addContactInformation(this.contact).subscribe(data => { }, error => { console.log(error) });
          }
        });
  }

  initializeCoverLetter() {
    this.coverLetterService.getCoverLetterInformation()
      .subscribe(
        data => {
          this.coverLetter = {
            "clid": data.clid,
            "headline": data.headline,
            "coverLetter": data.coverLetter,
          };

          if (this.coverLetter) {
            this.coverLetterForm.get('headlineField').setValue(this.coverLetter.headline);
            this.coverLetterForm.get('coverLetterField').setValue(this.coverLetter.coverLetter);
          }
        },
        error => {
          this.coverLetter = {
            "clid": 10,
            "headline": "HEADLINE",
            "coverLetter": "COVERLETTER",
          };

          if (this.coverLetter) {
            this.coverLetterForm.get('headlineField').setValue(this.coverLetter.headline);
            this.coverLetterForm.get('coverLetterField').setValue(this.coverLetter.coverLetter);

            this.coverLetterService.addCoverLetterInformation(this.coverLetter).subscribe(data => { }, error => { console.log(error) });
          }
        }
      );
  }

  initializeResume() {
    this.resumeForm.get('statementField').setValue("");

    this.initializeJobsTable();

    this.initializeProjectsTable();

    this.initializeEducationTable();
  }

  jobsDisplayColumns: string[] = ['name', 'location', 'websiteLink', 'started', 'ended', 'title', 'jobSummary', 'action'];
  jobs: Array<any> = [];
  jobsDataSource = new MatTableDataSource<any>(this.jobs);

  initializeJobsTable() {
    this.jobService.getAllJobsInformation().subscribe(data => {
      if (data) {
        if (data._embedded) {
          this.jobs = data._embedded.jobList;
          this.jobsDataSource = new MatTableDataSource(this.jobs);
          this.jobTable.renderRows();
        }
      }
    });
  }

  openAddJobDialog(): void {
    const dialogRef = this.dialog.open(JobDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Add Job", jid: 0, name: "", location: "", websiteLink: "", started: "", ended: "", title: "", jobSummary: "", jobFootnotes: null }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.jobService.addJobInformation(dialogResult).subscribe(
          serviceResult => {
            this.jobs.push(dialogResult);
            this.jobsDataSource = new MatTableDataSource(this.jobs);
            this.jobTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  openEditJobDialog(job: Job): void {
    const dialogRef = this.dialog.open(JobDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Edit Job", jid: job.jid, name: job.name, location: job.location, websiteLink: job.websiteLink, started: job.started, ended: job.ended, title: job.title, jobSummary: job.jobSummary, jobFootnotes: job.jobFootnotes }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        const jobIndex = this.jobs.findIndex(obj => obj.jid === job.jid);

        this.jobService.updateJobInformation(dialogResult).subscribe(
          result => {
            this.jobsDataSource.data[jobIndex] = dialogResult;
            this.jobs = this.jobsDataSource.data;
            this.jobsDataSource = new MatTableDataSource(this.jobs);
            this.jobTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  deleteJob(job: Job): void {
    const jobIndex = this.jobs.findIndex(obj => obj.jid === job.jid);

    this.jobService.removeJob(job.jid).subscribe(result => {
      this.jobsDataSource.data.splice(jobIndex, 1);
      this.jobs = this.jobsDataSource.data;
      this.jobsDataSource = new MatTableDataSource(this.jobs);
      this.jobTable.renderRows();
    }, error => { console.log(error) });
  }

  projectsDisplayColumns: string[] = ['name', 'location', 'title', 'summary', 'projectStart', 'projectEnd', 'websiteLink', 'repositoryLink', 'action'];
  projects: Array<any> = [];
  projectsDataSource = new MatTableDataSource<any>(this.projects);
  initializeProjectsTable() {
    this.projectService.getAllProjectsInformation().subscribe(data => {
      if (data) {
        if (data._embedded) {
          this.projects = data._embedded.projectList;
          this.projectsDataSource = new MatTableDataSource(this.projects);
          this.projectTable.renderRows();
        }
      }
    });
  }

  openAddProjectDialog(): void {
    const dialogRef = this.dialog.open(ProjectDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Add Project", pid: 0, name: "", location: "", title: "", summary: "", projectStart: "", projectEnd: "", websiteLink: "", repositoryLink: "" }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.projectService.addProjectInformation(dialogResult).subscribe(
          serviceResult => {
            this.projects.push(dialogResult);
            this.projectsDataSource = new MatTableDataSource(this.projects);
            this.projectTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  openEditProjectDialog(project: Project): void {
    const dialogRef = this.dialog.open(ProjectDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Edit Project", pid: project.pid, name: project.name, location: project.location, title: project.title, summary: project.summary, projectStart: project.projectStart, projectEnd: project.projectEnd, websiteLink: project.websiteLink, repositoryLink: project.repositoryLink }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        const projectIndex = this.projects.findIndex(obj => obj.pid === project.pid);

        this.projectService.updateProjectInformation(dialogResult).subscribe(
          result => {
            this.projectsDataSource.data[projectIndex] = dialogResult;
            this.projects = this.projectsDataSource.data;
            this.projectsDataSource = new MatTableDataSource(this.projects);
            this.projectTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  deleteProject(project: Project): void {
    const projectIndex = this.jobs.findIndex(obj => obj.pid === project.pid);

    this.projectService.removeProject(project.pid).subscribe(result => {
      this.projectsDataSource.data.splice(projectIndex, 1);
      this.projects = this.projectsDataSource.data;
      this.projectsDataSource = new MatTableDataSource(this.projects);
      this.projectTable.renderRows();
    }, error => { console.log(error) });
  }

  educationDisplayColumns: string[] = ['name', 'location', 'websiteLink', 'certification', 'started', 'ended', 'action'];
  education: Array<any> = [];
  educationDataSource = new MatTableDataSource<any>(this.education);
  initializeEducationTable() {
    this.educationService.getAllEducationInformation().subscribe(data => {
      if (data) {
        if (data._embedded) {
          this.education = data._embedded.educationList;
          this.educationDataSource = new MatTableDataSource(this.education);
          this.educationTable.renderRows();
        }
      }
    });
  }

  openAddEducationDialog(education: Education): void {
    const dialogRef = this.dialog.open(EducationDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Add Education", eid: 0, name: "", location: "", websiteLink: "", certification: "", started: "", ended: "" }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        this.educationService.addEducationInformation(dialogResult).subscribe(
          serviceResult => {
            this.education.push(dialogResult);
            this.educationDataSource = new MatTableDataSource(this.education);
            this.educationTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  openEditEducationDialog(education: Education): void {
    const dialogRef = this.dialog.open(EducationDialogComponent, {
      width: '450px',
      data: { _dialogtitle: "Edit Education", eid: education.eid, name: education.name, location: education.location, websiteLink: education.websiteLink, certification: education.certification, started: education.started, ended: education.ended }
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult) {
        const educationIndex = this.education.findIndex(obj => obj.eid === education.eid);
      
        this.educationService.updateEducationInformation(dialogResult).subscribe(
          result => {
            this.educationDataSource.data[educationIndex] = dialogResult;
            this.education = this.educationDataSource.data;
            this.educationDataSource = new MatTableDataSource(this.education);
            this.educationTable.renderRows();
          },
          error => { console.log(error) }
        );
      }
    });
  }

  deleteEducation(education: Education): void {
    const educationIndex = this.education.findIndex(obj => obj.eid === education.eid);

    this.educationService.removeEducation(education.eid).subscribe(result => {
      this.educationDataSource.data.splice(educationIndex, 1);
      this.education = this.educationDataSource.data;
      this.educationDataSource = new MatTableDataSource(this.education);
      this.educationTable.renderRows();
    }, error => { console.log(error) });
  }

  saveChanges(contactForm: FormGroup, coverLetterForm: FormGroup) {
    this.contact.firstName = contactForm.value.firstNameField.toString().trim();
    this.contact.lastName = contactForm.value.lastNameField.toString().trim();
    this.contact.message = contactForm.value.messageField.toString().trim();
    this.contact.email = contactForm.value.emailField.toString().trim();
    this.contact.githubAddress = contactForm.value.githubAddressField.toString().trim();
    this.contact.linkedinAddress = contactForm.value.linkedinAddressField.toString().trim();

    this.coverLetter.headline = coverLetterForm.value.headlineField.toString().trim();
    this.coverLetter.coverLetter = coverLetterForm.value.coverLetterField.toString().trim();

    this.contactService.updateContactInformation(this.contact).subscribe(data => { }, error => { console.log(error) });
    this.coverLetterService.addCoverLetterInformation(this.coverLetter).subscribe(data => { }, error => { console.log(error) });
  }
}
