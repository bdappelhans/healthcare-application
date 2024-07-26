import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const userRole = this.getUserRole();
    const isAdminRoute = route.data['admin'] as boolean;

    if (userRole === isAdminRoute) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  private getUserRole(): boolean | null {
    if (typeof localStorage !== 'undefined') {
      const userString = localStorage.getItem('user');
      if (!userString) {
        return false; // Default role if no user is found
      }
      const user = JSON.parse(userString);
      return user.admin;
    } else {
      return false; // Default role if localStorage is not available
    }
  }
}