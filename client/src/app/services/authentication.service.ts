import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  BASE_URL = "http://157.230.207.59:8761"


  constructor(private http: HttpClient) {
  }

  login(username, password) {
    return this.http.post<any>(this.BASE_URL + "/user/login", { "username": username, "password": password }, httpOptions).pipe(map(user => {
      if (user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
      }

      return user;
    }))
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
  }

  isLoggedIn() {
    if (localStorage.getItem('currentUser'))
      return true;
    else
      return false;
  }
}