import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';
import { Contact } from 'src/app/models/contact';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  contact: Contact;

  constructor(private contactService: ContactService) { }

  ngOnInit() {
    this.contactService.getContactInformation()
      .subscribe(data => { this.contact = data; }, error => { console.log(error) });
  }

  jumpToLink(url: string) {
    window.open(url, "_blank");
  }
}
