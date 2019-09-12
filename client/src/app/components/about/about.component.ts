import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { Contact } from 'src/app/models/contact';
import { CoverLetter } from 'src/app/models/coverletter';
import { CoverletterService } from 'src/app/services/coverletter.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  contact: Contact;
  coverLetter: CoverLetter;

  constructor(private contactService: ContactService, private coverLetterService: CoverletterService) { }

  ngOnInit() {
    this.contactService.getContactInformation()
      .subscribe(data => { this.contact = data; }, error => { console.log(error) });

    this.coverLetterService.getCoverLetterInformation().subscribe(data => { this.coverLetter = data }, error => { console.log(error) });
  }

  jumpToLink(url: string) {
    window.open(url, "_blank");
  }
}
