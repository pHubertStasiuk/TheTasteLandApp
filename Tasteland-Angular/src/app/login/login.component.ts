import { Component, OnInit } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { TokenStorage } from '../model/token.storage';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    usernameControl: new FormControl('', [Validators.required]),
    passwordControl: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private tokenStorage: TokenStorage, private router: Router) { }

  ngOnInit() {
  }

  login(): void {
    const user: User = {
      username: this.loginForm.get('usernameControl').value,
      password: this.loginForm.get('passwordControl').value
    };
    this.authService.attemptAuthorization(user).pipe(catchError(err => this.handleException(err)))
      .subscribe(
        response => {
          if (response.code === 'ACCESS_TOKEN') {
            console.log('dostep przyznany');
            this.tokenStorage.saveToken(response.token);
            this.router.navigate(['dashboard']);
          }
        }
      );
  }
  private handleException(error: Response | any) {

    let errMsg: string;
    console.log(error);
    if (error instanceof Response) {
      const body = JSON.parse(JSON.stringify(error)) || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''}
          ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.log(errMsg);
    return Observable.create(error);
  }
}
