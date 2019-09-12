import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Contact } from 'src/app/models/contact';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  contactForm: FormGroup;
  contact: Contact;
  firstName: string;
  lastName: string;
  email: string;
  githubAddress: string;
  linkedinAddress: string;

  constructor(private formBuilder: FormBuilder, private contactService: ContactService) { }

  ngOnInit() {
    this.contactService.getContactInformation()
      .subscribe(
        data => {
          this.contact = {
            "cid": 0,
            "firstName": data.firstName,
            "lastName": data.lastName,
            "email": data.email,
            "githubAddress": data.githubAddress,
            "linkedinAddress": data.linkedinAddress
          };

          this.contactForm = this.formBuilder.group({
            'firstNameField': new FormControl(this.firstName, [Validators.required, Validators.minLength(1)]),
            'lastNameField': new FormControl(this.lastName, [Validators.required, Validators.minLength(1)]),
            'emailField': new FormControl(this.email, [Validators.required, Validators.minLength(1)]),
            'githubAddressField': new FormControl(this.githubAddress, [Validators.required, Validators.minLength(1)]),
            'linkedinAddressField': new FormControl(this.linkedinAddress, [Validators.required, Validators.minLength(1)]),
          });

          if (this.contact) {
            this.contactForm.get('firstNameField').setValue(this.contact.firstName);
            this.contactForm.get('lastNameField').setValue(this.contact.lastName);
            this.contactForm.get('emailField').setValue(this.contact.email);
            this.contactForm.get('githubAddressField').setValue(this.contact.githubAddress);
            this.contactForm.get('linkedinAddressField').setValue(this.contact.linkedinAddress);
          }
        },
        error => {
          console.log(error);

          this.contact = {
            "cid": 0,
            "firstName": "FIRST NAME",
            "lastName": "LAST NAME",
            "email": "email@email.com",
            "githubAddress": "www.github.com",
            "linkedinAddress": "www.linkedin.com"
          };

          this.contactForm = this.formBuilder.group({
            'firstNameField': new FormControl(this.firstName, [Validators.required, Validators.minLength(1)]),
            'lastNameField': new FormControl(this.lastName, [Validators.required, Validators.minLength(1)]),
            'emailField': new FormControl(this.email, [Validators.required, Validators.minLength(1)]),
            'githubAddressField': new FormControl(this.githubAddress, [Validators.required, Validators.minLength(1)]),
            'linkedinAddressField': new FormControl(this.linkedinAddress, [Validators.required, Validators.minLength(1)]),
          });

          if (this.contact) {
            this.contactForm.get('firstNameField').setValue(this.contact.firstName);
            this.contactForm.get('lastNameField').setValue(this.contact.lastName);
            this.contactForm.get('emailField').setValue(this.contact.email);
            this.contactForm.get('githubAddressField').setValue(this.contact.githubAddress);
            this.contactForm.get('linkedinAddressField').setValue(this.contact.linkedinAddress);
          }
        });
  }

  saveChanges(contactForm: FormGroup) {
    this.contact.firstName = contactForm.value.firstNameField.toString().trim();
    this.contact.lastName = contactForm.value.lastNameField.toString().trim();
    this.contact.email = contactForm.value.emailField.toString().trim();
    this.contact.githubAddress = contactForm.value.githubAddressField.toString().trim();
    this.contact.linkedinAddress = contactForm.value.linkedinAddressField.toString().trim();

    this.contactService.addContactInformation(this.contact).subscribe(data => { console.log(data) }, error => { console.log(error) });
  }
}
