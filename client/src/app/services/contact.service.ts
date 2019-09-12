import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Contact } from '../models/contact';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getContactInformation() {
    return this.http
      .get<Contact>(this.BASE_URL + "contact");
  }

  addContactInformation(contact: Contact) {
    console.log("FROM SERVICE: " + JSON.stringify(contact));
    return this.http
      .post(this.BASE_URL + "contact", JSON.stringify(contact), httpOptions)
  }

  updateContactInformation(contact: Contact) {
    return this.http
      .put(this.BASE_URL + "contact", JSON.stringify(contact), httpOptions)
  }
}
