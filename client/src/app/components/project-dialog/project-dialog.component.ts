import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-project-dialog',
  templateUrl: './project-dialog.component.html',
  styleUrls: ['./project-dialog.component.css']
})
export class ProjectDialogComponent {
  projectForm = new FormGroup({
    nameField: new FormControl([Validators.required, Validators.minLength(1)]),
    locationField: new FormControl([Validators.required, Validators.minLength(1)]),
    titleField: new FormControl([Validators.required, Validators.minLength(1)]),
    summaryField: new FormControl([Validators.required, Validators.minLength(1)]),
    startedField: new FormControl([Validators.required, Validators.minLength(1)]),
    endedField: new FormControl([Validators.required, Validators.minLength(1)]),
    websiteLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
    repositoryLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
  });

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<ProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public project: Project) {

    this.projectForm.get('nameField').setValue(this.project.name.trim());
    this.projectForm.get('locationField').setValue(this.project.location.trim());
    this.projectForm.get('titleField').setValue(this.project.websiteLink.trim());
    this.projectForm.get('summaryField').setValue(this.project.summary.trim());
    this.projectForm.get('startedField').setValue(this.project.projectStart.trim());
    this.projectForm.get('endedField').setValue(this.project.projectEnd.trim());
    this.projectForm.get('websiteLinkField').setValue(this.project.websiteLink.trim());
    this.projectForm.get('repositoryLinkField').setValue(this.project.repositoryLink.trim());
  }

  onSaveClick(): void {
    delete this.project._dialogtitle;

    this.project.name = this.projectForm.value.nameField.toString().trim();
    this.project.location = this.projectForm.value.locationField.toString().trim();
    this.project.title = this.projectForm.value.titleField.toString().trim();
    this.project.summary = this.projectForm.value.summaryField.toString().trim();
    this.project.projectStart = this.projectForm.value.startedField.toString().trim();
    this.project.projectEnd = this.projectForm.value.endedField.toString().trim();
    this.project.websiteLink = this.projectForm.value.websiteLinkField.toString().trim();
    this.project.repositoryLink = this.projectForm.value.repositoryLinkField.toString().trim();

    this.dialogRef.close(this.project);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
