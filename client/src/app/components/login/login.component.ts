import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  username: string;
  password: string;
  incorrectLogin: boolean;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    this.incorrectLogin = false;
    this.loginForm = this.formBuilder.group({
      'username': new FormControl(this.username, [Validators.required, Validators.minLength(1)]),
      'password': new FormControl(this.password, [Validators.required, Validators.minLength(1)]),
    })
  }

  login(loginForm) {
    this.username = loginForm.value.username.toString().trim();
    this.password = loginForm.value.password.toString().trim();

    this.authService.login(this.username, this.password).pipe(first()).subscribe(data => {
      this.incorrectLogin = false;
      this.router.navigate(["/"]);
    }, error => {
      this.incorrectLogin = true;
      console.log(error);
    });
  }
}
