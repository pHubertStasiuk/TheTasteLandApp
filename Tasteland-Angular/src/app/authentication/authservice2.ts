// import { Injectable, OnInit } from '@angular/core';
// import { Observable, Observer } from 'rxjs';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Router } from '@angular/router';
// import { environment } from 'src/environments/environment';
// import { User } from '../model/User';
// import { ExecutionStatus } from '../model/ExecutionStatus';


// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService implements OnInit {
//   areCredentialsCorrect: boolean;
//   isLoggedIn: Observable<boolean>;
//   private observer: Observer<boolean>;
//   private authToken: string;
//   serverUrl: string;
//   executionStatus: ExecutionStatus;
//   constructor(private http: HttpClient, private router: Router) { }

//   ngOnInit() {
//     this.isLoggedIn = new Observable(observer => this.observer = observer);
//     this.serverUrl = environment.serverUrl;
//     this.areCredentialsCorrect = false;
//   }
//   login(user: User): Observable<ExecutionStatus> {
//     const url = this.serverUrl + '/user/login';
//     const headers = this.getHTTPHeaders();
//     return this.http.post(url, user, { headers: headers, withCredentials: true })
//       .map(response => {
//         const body = response.json();
//         const executionStatus: ExecutionStatus = ({
//           code: body.code,
//           message: body.message,
//           object: body.object
//         });
//         this.executionStatus = executionStatus;
//         return executionStatus;
//       }).subscribe(body => {
//         if (body.code === 'USER_LOGIN_SUCCESS') {
//           this.changeLoginStatus(true);
//         }
//       }).catch(error => this.handleError(error));
//   }
//   private getHTTPHeaders(): HttpHeaders {
//     const header = new HttpHeaders({});
//     header.append('Accept', 'application/json');
//     return header;
//   }

//   logout(): void {
//     this.changeLoginStatus(true);
//   }
//   private changeLoginStatus(status: boolean) {
//     if (this.observer !== undefined) {
//       this.observer.next(status);
//     }
//   }
//   private handleError(error: Response | any) {
//     let errMsg: string;
//     console.log(error);
//     if (error instanceof Response) {
//       const body = JSON.parse(JSON.stringify(error)) || '';
//       const err = body.error || JSON.stringify(body);
//       errMsg = `${error.status} - ${error.statusText || ''}
//       ${err}`;
//     } else {
//       errMsg = error.message ? error.message : error.toString();
//     }
//     return error;
//   }


//   public getAuthToken(): string {
//     return this.authToken;
//   }

//   public isAuthenticated(token: string): Observable<boolean> {


//     return this.isLoggedIn.subscribe();
//     return null;
//   }
// }



