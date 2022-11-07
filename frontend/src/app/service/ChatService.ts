import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

import {Storage} from "./Storage";
import {Room} from "../components/model/Room";
import {Message} from "../components/model/Message";

@Injectable({ providedIn: 'root' })
export class ChatService {
  //readonly url = 'https://benatti-taxi-service.herokuapp.com/chats';
  readonly url = 'http://localhost:8080/chats';
  errorMessage: string | undefined;

  constructor(private http: HttpClient,
              private storage: Storage) {}

  getHomePage() {
    return this.http.get<Room[]>(this.url, this.createHeaders());
  }

  sendMessage(id: number, messageText:string) {
    return this.http.post<Message>(this.url + '/' + id, messageText, this.createHeaders());
  }

  createHeaders() {
    return {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
        .append('Access-Control-Allow-Headers', 'Content-Type')
        .append('Access-Control-Allow-Methods', 'GET')
        .append('Access-Control-Allow-Origin', '*')
        .append('Authorization', 'Bearer ' + this.storage.getToken())
    };
  }

  getMessagesFromRoom(id: number, pageNumber: number) {
    return this.http.get<Message[]>(this.url + '/' + id + (pageNumber === 0 ? '' : '?' + pageNumber) , this.createHeaders())
  }
}
