import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

@Injectable()
export class TokenStorage {

  constructor() {}

  public saveToken(token: string) {
    window.sessionStorage.removeItem(environment.TOKEN_KEY);
    window.sessionStorage.setItem(environment.TOKEN_KEY,  token);
  }

  public getToken(): string {
    return window.sessionStorage.getItem(environment.TOKEN_KEY);
  }

  public isValid(): boolean {
    return this.getToken() !== null;
  }
}
