import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Contact } from 'src/app/models/contact';
import { CoverletterService } from 'src/app/services/coverletter.service';
import { CoverLetter } from 'src/app/models/coverletter';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
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

  constructor(private formBuilder: FormBuilder, private contactService: ContactService, private coverLetterService: CoverletterService) { }

  ngOnInit() {
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

      this.resumeForm.get('statementField').setValue("");
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
