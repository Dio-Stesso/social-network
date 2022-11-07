import { Component, OnInit } from '@angular/core';
import {Storage} from "../../service/Storage";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  providers: [Storage],
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private storage: Storage) { }

  ngOnInit(): void {
  }

  logOut() {
    this.storage.signOut();
    // @ts-ignore
    window.location = window.location.origin + '/login';
  }

  toUsers() {
    // @ts-ignore
    window.location = window.location.origin + '/users';
  }
}
