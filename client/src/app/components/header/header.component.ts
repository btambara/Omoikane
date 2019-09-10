import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  @Output() toggleSidenav = new EventEmitter<void>();


  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {

  }
  
  get isLoggedIn() { return this.authService.isLoggedIn() };

  logout() {
    this.authService.logout();
    this.router.navigate(["/login"]);
  }
}
