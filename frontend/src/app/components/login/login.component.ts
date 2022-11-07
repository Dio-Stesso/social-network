import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../service/AuthenticationService";
import {Storage} from "../../service/Storage";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [AuthenticationService, Storage],
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  username: string | undefined;
  password: string | undefined;
  errorMessage: string | undefined;

  constructor(private http: HttpClient,
              private authService: AuthenticationService,
              private storage: Storage) { }

  ngOnInit(): void {
    //console.log(window.location.origin);
    // @ts-ignore
    console.log(window.location.pathname);
  }

  login() {
    this.authService.postLogin({username:this.username, password:this.password}).pipe(catchError(err => {
      this.errorMessage = "Login or password was incorrect!";
      return [];
    })).subscribe(response => {
      this.storage.saveToken(response.token);
      this.authService.checkToken();
    });
  }

  get() {
    this.authService.get().subscribe(response => {
      console.log(response);
    })
  }

  getToken() {
    console.log(this.storage.getToken());
  }

  toRegister() {
    // @ts-ignore
    window.location = window.location.origin + '/register';
  }
}

interface User {
  username:string | undefined;
  password:string | undefined;
}
