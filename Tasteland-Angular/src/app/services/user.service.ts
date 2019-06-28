import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serverUrl: string;

  constructor(private http: HttpClient) {
    this.serverUrl = environment.serverUrl;
   }



}
