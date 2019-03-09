import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SearchBoxComponent } from './navbar/search-box/search-box.component';
import { NotificationControlsComponent } from './navbar/notification-controls/notification-controls.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterialModule } from '../material.module';
import { UserProfileComponent } from './navbar/user-profile/user-profile.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NgbModule,
    MaterialModule
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    SearchBoxComponent,
    NotificationControlsComponent,
    UserProfileComponent
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent
  ]
})
export class ComponentsModule { }
