
import { Injectable } from '@angular/core';

import { MatSidenav } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class SidenavService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"
  
  private sidenav: MatSidenav;
  private opened = false;

  public setSidenav(sidenav: MatSidenav) {
    this.sidenav = sidenav;
  }

  public open() {
    this.opened = true;
    return this.sidenav.open();
  }

  public close() {
    this.opened = false;
    return this.sidenav.close();
  }

  public toggle() {
    this.opened = !this.opened;
    return this.sidenav.toggle();
  }

  public isOpen(): boolean {
    return this.opened;
  }
}