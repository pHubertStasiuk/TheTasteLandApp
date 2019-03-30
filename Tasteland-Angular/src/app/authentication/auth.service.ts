import { Injectable, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  private serverUrl: string;
  constructor(private http: HttpClient) { }
  ngOnInit() {
    this.serverUrl = environment.serverUrl;
  }
  attemptAuthorization(user: User): Observable<any> {
    const url = this.serverUrl + '/auth/token';
    return this.http.post(url, user);
  }
}
