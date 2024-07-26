import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RouterOutlet, HttpClientModule],
  providers: [UserService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user: User = {email: '', password: '', firstName: '', lastName: '', admin: false};

  constructor(private userService: UserService, private router: Router) { }

  attemptLogin(user: User) {
    console.log("User details entered:");
    console.log(user);

    this.userService.getUserByEmail(user.email).subscribe(
      (foundUser: User) => {

        console.log("User details found:");
        console.log(foundUser);

        if (!foundUser) {
          alert("User not found");
          return;
        }
        
        if (foundUser.password !== user.password) {
          alert("Incorrect password");
          return;
        }

        if ((foundUser.admin && !user.admin) || (!foundUser.admin && user.admin)) {
          alert("Incorrect role");
          return;
        }

         // Store user info in local storage
         localStorage.setItem('user', JSON.stringify(foundUser));

        if (foundUser.admin) {
          this.router.navigate(['/admin']);
        } else if (!foundUser.admin) {
          this.router.navigate(['/user']);
        }
        
      },
      (error) => {
        console.error('There was an error fetching user details: ', error);
        alert("Unable to find user");
  });
  }

  navigateToRegister() {
    this.router.navigate(['/register']);
  }
}
