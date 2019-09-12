import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CoverLetter } from '../models/coverletter';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CoverletterService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getCoverLetterInformation() {
    return this.http
      .get<CoverLetter>(this.BASE_URL + "coverletter");
  }

  addCoverLetterInformation(coverletter: CoverLetter) {
    return this.http
      .post(this.BASE_URL + "coverletter", JSON.stringify(coverletter), httpOptions)
  }

  updateCoverLetterInformation(coverletter: CoverLetter) {
    return this.http
      .put(this.BASE_URL + "coverletter", JSON.stringify(coverletter), httpOptions)
  }
}
