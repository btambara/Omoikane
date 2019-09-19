import { Component, Inject } from '@angular/core';
import { Education } from 'src/app/models/education';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-education-dialog',
  templateUrl: './education-dialog.component.html',
  styleUrls: ['./education-dialog.component.css']
})
export class EducationDialogComponent {
  educationForm = new FormGroup({
    nameField: new FormControl([Validators.required, Validators.minLength(1)]),
    locationField: new FormControl([Validators.required, Validators.minLength(1)]),
    websiteLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
    certificationField: new FormControl([Validators.required, Validators.minLength(1)]),
    startedField: new FormControl([Validators.required, Validators.minLength(1)]),
    endedField: new FormControl([Validators.required, Validators.minLength(1)]),
  });

  constructor(private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EducationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public education: any) {
    this.educationForm.get('nameField').setValue(this.education.name.trim());
    this.educationForm.get('locationField').setValue(this.education.location.trim());
    this.educationForm.get('websiteLinkField').setValue(this.education.websiteLink.trim());
    this.educationForm.get('certificationField').setValue(this.education.certification.trim());
    this.educationForm.get('startedField').setValue(this.education.started.trim());
    this.educationForm.get('endedField').setValue(this.education.ended.trim());
  }

  onSaveClick(): void {
    delete this.education._dialogtitle;

    this.education.name = this.educationForm.value.nameField.toString().trim();
    this.education.location = this.educationForm.value.locationField.toString().trim();
    this.education.websiteLink = this.educationForm.value.websiteLinkField.toString().trim();
    this.education.certification = this.educationForm.value.certificationField.toString().trim();
    this.education.started = this.educationForm.value.startedField.toString().trim();
    this.education.ended = this.educationForm.value.endedField.toString().trim();

    this.dialogRef.close(this.education);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
