import { Component, OnInit } from '@angular/core';
import {Storage} from "./service/Storage";
import {AuthenticationService} from "./service/AuthenticationService";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [Storage, AuthenticationService],
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.authService.checkToken();
  }

}
