import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/authentication/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  private user: User;
  constructor(private userService: AuthService) { }

  ngOnInit() {

    const user: User = {
      username: 'John Smith',
      password: 'Administrator'

    };

    this.user = user;
  }
  getUserAvatar(): string {
    return 'https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cg_face%2Cq_auto:good%2Cw_300/MTU0NjQzOTk4OTQ4OTkyMzQy/ansel-elgort-poses-for-a-portrait-during-the-baby-driver-premiere-2017-sxsw-conference-and-festivals-on-march-11-2017-in-austin-texas-photo-by-matt-winkelmeyer_getty-imagesfor-sxsw-square.jpg';
  }

  getUserBackground(): string {

    return 'http://wallpapere.org/wp-content/uploads/2012/02/black-and-white-city-night.png';
  }

  getUserFirstName(): string {
    return this.user.username;
  }

  getUserRole(): string {
    return this.user.password;
  }
}
