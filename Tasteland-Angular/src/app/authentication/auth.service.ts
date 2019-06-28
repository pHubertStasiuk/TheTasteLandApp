import { Injectable, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { TokenStorage } from '../model/token.storage';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private serverUrl: string;
  constructor(private http: HttpClient, private tokenStorage: TokenStorage, private router: Router) {
    this.serverUrl = environment.serverUrl;
  }

  attemptAuthorization(user: User): Observable<any> {
    const url = this.serverUrl + '/authenticate/token';
    return this.http.post(url, user);
  }

  isAuthenticated(): boolean {
    return this.tokenStorage.isValid();
  }

  signOut() {
    window.sessionStorage.removeItem(environment.TOKEN_KEY);
    window.sessionStorage.clear();
    this.router.navigate(['login']);
  }

  register(user: User): Observable<any> {
    console.log('auth serv');
    const url = this.serverUrl + '/user/register';
    return this.http.post(url, user);
  }

}

