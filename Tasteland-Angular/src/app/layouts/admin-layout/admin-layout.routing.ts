import { Routes } from '@angular/router';
import { DashboardComponent } from 'src/app/components/content/dashboard/dashboard.component';
import { LoginComponent } from 'src/app/login/login.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard', component: DashboardComponent }

];
