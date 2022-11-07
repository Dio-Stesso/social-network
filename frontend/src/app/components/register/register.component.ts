import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/AuthenticationService";
import {RegisterUser} from "../model/RegisterUser";
import {catchError} from "rxjs/operators";
import {Storage} from "../../service/Storage";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  providers: [AuthenticationService,
    Storage],
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  email: string | undefined;
  username: string | undefined;
  password: string | undefined;
  repeatPassword: string | undefined;
  errorMessage: string | undefined;

  constructor(private authService: AuthenticationService,
              private storage: Storage) { }

  ngOnInit(): void {
  }

  register() {
    this.authService.postRegister({username: this.username,
      password: this.password,
      repeatPassword: this.repeatPassword,
      email:this.email} as RegisterUser).pipe(catchError(err => {
      this.errorMessage = "Incorrect data!";
      return [];
    })).subscribe(response => {
      // @ts-ignore
      this.authService.postLogin({username: this.username,
        password: this.password}).subscribe(response => {
        this.storage.saveToken(response.token);
        this.authService.checkToken();
      });
    });
  }

  toLogin() {
    // @ts-ignore
    window.location = window.location.origin + '/login';
  }
}
