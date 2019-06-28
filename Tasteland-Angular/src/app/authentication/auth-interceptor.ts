import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorage } from '../model/token.storage';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenStorage: TokenStorage, private router: Router) { }

  intercept(httpRequest: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest: any;
    if (this.tokenStorage.getToken() != null) {
      authRequest = httpRequest.clone({
        headers: httpRequest.headers.set(environment.authHeader,
          'Bearer ' + this.tokenStorage.getToken())
      });
    }
    return next.handle(authRequest).do(
      (error: any) => {
        console.log('status: ' + error.status);
        if (error instanceof HttpErrorResponse && error.status === 401) {
          this.router.navigate(['login']);
        }
      }
    );
  }
}
