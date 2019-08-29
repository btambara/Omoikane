import { Component } from '@angular/core';
import { MatSidenav } from '@angular/material';
import { SidenavService } from './services/sidenav.service';

interface ROUTE {
  icon?: string;
  route?: string;
  title?: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public sidenav: MatSidenav;

  title = 'BrianTambara.tk';

  navRoutes: ROUTE[] = [
    {
      icon: 'people',
      route: 'about',
      title: 'About'
    },
    {
      icon: 'notes',
      route: 'resume',
      title: 'Resume'
    },
  ];

  constructor(private commandBarSidenavService: SidenavService) {
    commandBarSidenavService.setSidenav(this.sidenav);
  }
}
