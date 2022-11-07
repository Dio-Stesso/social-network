import { Component, OnInit } from '@angular/core';
import {Storage} from "../../service/Storage";
import {UserService} from "../../service/UserService";
import {User} from "../model/User";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  providers: [UserService, Storage],
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  username: string | undefined;
  users: User[] | undefined;

  constructor(private service: UserService) { }

  ngOnInit(): void {
    this.service.getHomePage().subscribe(response => this.users = response)
  }

  createRoom(username: string) {
    if (this.username !== '') {
      this.service.createRoom(username).subscribe(response => {
        //console.log(response.id)
        // @ts-ignore
        window.location = window.location.origin + '/chats';
      });
    }
  }
}
