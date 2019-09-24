import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';

@Component({
  selector: 'app-project-dialog',
  templateUrl: './project-dialog.component.html',
  styleUrls: ['./project-dialog.component.css']
})
export class ProjectDialogComponent {
  footnoteArr: FormArray;
  projectForm: FormGroup;
  visible = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<ProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public project: any) {
    if (this.project.technicalEnvironment == null) {
      this.project.technicalEnvironment = new Array<String>();
    }

    this.projectForm = new FormGroup({
      nameField: new FormControl([Validators.required, Validators.minLength(1)]),
      locationField: new FormControl([Validators.required, Validators.minLength(1)]),
      titleField: new FormControl([Validators.required, Validators.minLength(1)]),
      summaryField: new FormControl([Validators.required, Validators.minLength(1)]),
      startedField: new FormControl([Validators.required, Validators.minLength(1)]),
      endedField: new FormControl([Validators.required, Validators.minLength(1)]),
      websiteLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
      repositoryLinkField: new FormControl([Validators.required, Validators.minLength(1)]),
      technicalNoteArr: this.formBuilder.array(this.project.technicalEnvironment),
    });

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
    this.project.technicalEnvironment = this.projectForm.value.technicalNoteArr;
    console.log(this.project);

    this.dialogRef.close(this.project);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  addTechnicalNote(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.projectForm.value.technicalNoteArr.push(value.trim());
    }

    if (input) {
      input.value = '';
    }
  }

  removeTechnicalNote(tech: string): void {
    const index = this.projectForm.value.technicalNoteArr.indexOf(tech);

    if (index >= 0) {
      this.projectForm.value.technicalNoteArr.splice(index, 1);
    }
  }
}
