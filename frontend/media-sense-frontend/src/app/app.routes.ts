import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing/landing-page/landing-page.component';
import { LoginComponent } from './components/landing/login/login.component';
import { SignupComponent } from './components/landing/signup/signup.component';

export const routes: Routes = [
    {path: "", component: LandingPageComponent},
    {path: "login", component: LoginComponent},
    {path: "signup", component: SignupComponent},
    {path: "app", component: LandingPageComponent}, // Placeholder for the main app component

];
