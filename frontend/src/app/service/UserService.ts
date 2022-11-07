import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

import {Storage} from "./Storage";
import {User} from "../components/model/User";
import {AuthenticationService} from "./AuthenticationService";
import {Room} from "../components/model/Room";

@Injectable({ providedIn: 'root' })
export class UserService {
  readonly url = 'http://localhost:8080/users';
  //readonly url = 'https://benatti-taxi-service.herokuapp.com/users';
  errorMessage: string | undefined;


  constructor(private http: HttpClient,
              private storage: Storage,
              private authenticaton: AuthenticationService) {}

  getHomePage() {
    return this.http.get<User[]>(this.url, this.createHeaders());
  }

  createRoom(username: string | undefined) {
    return this.http.post<Room>(this.url, username, this.createHeaders());
  }

  createHeaders() {
    return {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
        .append('Access-Control-Allow-Headers', 'Content-Type')
        .append('Access-Control-Allow-Methods', '*')
        .append('Access-Control-Allow-Origin', '*')
        .append('Authorization', 'Bearer ' + this.storage.getToken())
    };
  }
}
