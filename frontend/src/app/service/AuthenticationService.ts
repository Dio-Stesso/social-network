import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

import {LoginUser} from "../components/model/LoginUser";
import {AuthResponse} from "../components/model/AuthResponse";
import {RegisterUser} from "../components/model/RegisterUser";
import {Storage} from "./Storage";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  //readonly url = 'https://benatti-taxi-service.herokuapp.com/';//remote control
  readonly url = 'http://localhost:8080/';
  errorMessage: string | undefined;

  constructor(private readonly http: HttpClient,
              private storage: Storage) {}

  postLogin(user: LoginUser) {
    return this.http.post<AuthResponse>(this.url + 'login', user, this.createHeaders());
  }

  get() {
    return this.http.get(this.url);
  }

  postRegister(user: RegisterUser) {
    return this.http.post<AuthResponse>(this.url + 'register',
      user, this.createHeaders())
  }

  checkToken() {
    let token = this.storage.getToken();
    if (token === null) {
      if (window.location.pathname != '/login'
        && window.location.pathname != '/register') {
        // @ts-ignore
        window.location = window.location.origin + '/login';
      }
    } else {
      if (window.location.pathname === '/login'
        || window.location.pathname === '/register') {
        // @ts-ignore
        window.location = window.location.origin;
      }
      console.log("Please add method who be checking token");
    }
  }

  createHeaders() {
    return {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
        .append('Access-Control-Allow-Headers', 'Content-Type')
        .append('Access-Control-Allow-Methods', '*')
        .append('Access-Control-Allow-Origin', '*')
    };
  }
}
