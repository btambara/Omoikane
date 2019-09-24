import { Component, Inject } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';

@Component({
  selector: 'app-job-dialog',
  templateUrl: './job-dialog.component.html',
  styleUrls: ['./job-dialog.component.css']
})
export class JobDialogComponent {
  technicalNoteArr: FormArray;
  footnoteArr: FormArray;
  jobForm: FormGroup;
  visible = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<JobDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public job: any) {

    if (this.job.jobFootnotes == null) {
      this.job.jobFootnotes = new Array<String>();
    }
    if (this.job.technicalEnvironment == null) {
      this.job.technicalEnvironment = new Array<String>();
    }

    this.jobForm = new FormGroup({
      nameField: new FormControl([Validators.required, Validators.minLength(1)]),
      locationField: new FormControl([Validators.required, Validators.minLength(1)]),
      websiteLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
      startedField: new FormControl([Validators.required, Validators.minLength(1)]),
      endedField: new FormControl([Validators.required, Validators.minLength(1)]),
      titleField: new FormControl([Validators.required, Validators.minLength(1)]),
      jobSummaryField: new FormControl([Validators.required, Validators.minLength(1)]),
      footnoteArr: this.formBuilder.array(this.job.jobFootnotes),
      technicalNoteArr: this.formBuilder.array(this.job.technicalEnvironment),
    });

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
    this.job.jobFootnotes = this.jobForm.value.footnoteArr;
    this.job.technicalEnvironment = this.jobForm.value.technicalNoteArr;

    this.dialogRef.close(this.job);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  addFootnote(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.jobForm.value.footnoteArr.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
  }

  removeFootnote(footnote: string): void {
    const index = this.jobForm.value.footnoteArr.indexOf(footnote);

    if (index >= 0) {
      this.jobForm.value.footnoteArr.splice(index, 1);
    }
  }

  addTechnicalNote(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.jobForm.value.technicalNoteArr.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
  }

  removeTechnicalNote(tech: string): void {
    const index = this.jobForm.value.technicalNoteArr.indexOf(tech);

    if (index >= 0) {
      this.jobForm.value.technicalNoteArr.splice(index, 1);
    }
  }
}
