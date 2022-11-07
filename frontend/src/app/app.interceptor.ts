import { Injectable } from '@angular/core';
import {
  HttpInterceptor, HttpRequest, HttpHandler,
  HttpErrorResponse, HttpEvent
} from '@angular/common/http';
import { Router } from '@angular/router';
import 'rxjs/add/operator/do';
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Storage} from "./service/Storage";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: Storage, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const token = this.token.getToken();
    if (token) {
      req = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this .token.getToken())});
    }
    // @ts-ignore
    return next.handle(req).pipe(
      catchError(err => {
        if (err instanceof HttpErrorResponse && err.status === 401) {
          this.router.navigate(['login']);
        }
      return throwError(err);
      })
    );
  }
}
