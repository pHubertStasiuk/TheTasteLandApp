import { Component, OnInit } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { TokenStorage } from '../model/token.storage';
import { User } from '../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user: User;

  constructor(private authService: AuthService, private tokenStorage: TokenStorage, private router: Router) { }

  ngOnInit() {
    this.user = { username: 'test', password: 'tralala' };
  }

  login(): void {
    this.authService.attemptAuthorization(this.user).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.router.navigate(['dashboard']);
      }
    );
  }
}
