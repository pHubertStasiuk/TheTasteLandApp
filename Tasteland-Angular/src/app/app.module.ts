import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ComponentsModule } from './components/components.module';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { FormsModule } from '@angular/forms';
import { ScrollToTopDirective } from './directives/scroll-to-top.directive';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    ScrollToTopDirective,
    LoginComponent
    ],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    RouterModule,
    ComponentsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
