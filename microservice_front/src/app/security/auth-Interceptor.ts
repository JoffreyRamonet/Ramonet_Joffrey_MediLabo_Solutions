import {Injectable} from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from "./auth-service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  token!: null | string;

  constructor(private authService: AuthService) {
    this.token = this.authService.token;
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if (this.token) {
      const authReq = req.clone({
        setHeaders:{
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': `Bearer ${this.token}`,
        }

      });
      console.log("interceptor " + this.token)
      return next.handle(authReq);
    }

    return next.handle(req);
  }
}
