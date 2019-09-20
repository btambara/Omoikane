import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Statement } from 'src/app/models/statement';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StatementService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getStatementInformation() {
    return this.http
      .get<Statement>(this.BASE_URL + "statement");
  }

  addStatementrInformation(statement: Statement) {
    return this.http
      .post(this.BASE_URL + "statement", JSON.stringify(statement), httpOptions)
  }

  updateStatementInformation(statement: Statement) {
    return this.http
      .put(this.BASE_URL + "statement", JSON.stringify(statement), httpOptions)
  }
}
