import { Component, OnInit } from '@angular/core';

declare interface MenuItem {
  path: string;
  title: string;
  icon?: string;
  class?: string;
  child?: Array<MenuItem>;
}
export const menuItems: MenuItem[] = [
  { path: '/dashboard', title: 'Dashboard', icon: 'fa fa-home' },
  {
    path: '/recipes', title: 'Recipes', icon: 'fa fa-cutlery', child: [
      { path: '/showList', title: 'My Recipes', icon: 'fa fa-cutlery' },
      { path: '/showFavourite', title: 'Favourites', icon: 'fa fa-cutlery' },
      { path: '/addNew', title: 'Add Recipe', icon: 'fa fa-cutlery' }]
  },
  { path: '/notifications', title: 'Notifications', icon: 'fa fa-globe' },
  {
    path: '/settings', title: 'Settings', icon: 'fa fa-cog',
    child: [
      { path: '/logout', title: 'Logout', icon: 'fa fa-cutlery' },
      { path: '/profie', title: 'Profile', icon: 'fa fa-cutlery' }]
  }



];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = menuItems.filter(menuItem => menuItem);
  }
}
