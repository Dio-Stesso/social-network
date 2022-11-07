import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ChatService} from "../../service/ChatService";
import {Storage} from "../../service/Storage";
import {Room} from "../model/Room";
import {Message} from "../model/Message";
import {Stomp} from "@stomp/stompjs";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  providers: [Storage, ChatService],
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  rooms: Room[] | undefined;
  pageNumber: number = 0;
  messages: Message[] | undefined;
  roomName: string | undefined;
  text: string | undefined;
  id: number | undefined;
  ws: any;

  constructor(private http: HttpClient,
              private service: ChatService,
              private storage: Storage) {

  }

  ngOnInit(): void {
    this.service.getHomePage().subscribe(response => this.rooms = response);
    console.log("Start")
    //this.connection();
  }

  openRoom(id: number, name: string | undefined) {
    if (this.id != id) {
      this.id = id;
      this.service.getMessagesFromRoom(id, this.pageNumber).subscribe(response => {
        this.roomName = name;
        this.messages = response
      });
      this.connection();
    }
    console.log(this.messages)
  }

  sendMessage() {
    if (this.id != undefined && this.text != undefined) {
      this.service.sendMessage(this.id, this.text).subscribe(response => {
        this.messages?.push(response)
        this._send(response);
      });
      this.text = undefined;
    }
  }

  connection() {
    this.ws = Stomp.client('ws://localhost:8080/sba-websocket');
    this.ws.connect({}, (frame: any) => {
      this.ws.subscribe('/topic/activity/' + this.id, (greeting: any) => {
        console.log(greeting.body)
        let message = JSON.parse(greeting.body);
        if (message.senderName === this.roomName) {
          this.messages?.push(message)
        }
      });
    }, this.onError);
  }

  onError(msg: string) {
    console.log(msg);
  }

  _send(message: Message) {
    this.ws.send('/topic/activity/' + this.id, {}, JSON.stringify(message));
  }
}
