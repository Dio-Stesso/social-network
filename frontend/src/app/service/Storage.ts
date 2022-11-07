import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';

@Injectable()
export class Storage {

  constructor() { }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,  token);
  }

  public getToken() {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
}
