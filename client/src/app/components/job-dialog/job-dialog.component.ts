import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Job } from 'src/app/models/job';

@Component({
  selector: 'app-job-dialog',
  templateUrl: './job-dialog.component.html',
  styleUrls: ['./job-dialog.component.css']
})
export class JobDialogComponent {
  jobForm = new FormGroup({
    nameField: new FormControl([Validators.required, Validators.minLength(1)]),
    locationField: new FormControl([Validators.required, Validators.minLength(1)]),
    websiteLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
    startedField: new FormControl([Validators.required, Validators.minLength(1)]),
    endedField: new FormControl([Validators.required, Validators.minLength(1)]),
    titleField: new FormControl([Validators.required, Validators.minLength(1)]),
    jobSummaryField: new FormControl([Validators.required, Validators.minLength(1)]),
  });


  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<JobDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public job: Job) {

    this.jobForm.get('nameField').setValue(this.job.name.trim());
    this.jobForm.get('locationField').setValue(this.job.location.trim());
    this.jobForm.get('websiteLinkField').setValue(this.job.websiteLink.trim());
    this.jobForm.get('startedField').setValue(this.job.started.trim());
    this.jobForm.get('endedField').setValue(this.job.ended.trim());
    this.jobForm.get('titleField').setValue(this.job.title.trim());
    this.jobForm.get('jobSummaryField').setValue(this.job.jobSummary.trim());
  }

  onSaveClick(): void {
    delete this.job._dialogtitle;

    this.job.name = this.jobForm.value.nameField.toString().trim();
    this.job.location = this.jobForm.value.locationField.toString().trim();
    this.job.websiteLink = this.jobForm.value.websiteLinkField.toString().trim();
    this.job.started = this.jobForm.value.startedField.toString().trim();
    this.job.ended = this.jobForm.value.endedField.toString().trim();
    this.job.title = this.jobForm.value.titleField.toString().trim();
    this.job.jobSummary = this.jobForm.value.jobSummaryField.toString().trim();

    this.dialogRef.close(this.job);
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
}
