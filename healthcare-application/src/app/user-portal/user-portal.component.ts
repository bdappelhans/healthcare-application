import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet, Router } from '@angular/router';

@Component({
  selector: 'app-user-portal',
  standalone: true,
  imports: [RouterOutlet, RouterModule],
  templateUrl: './user-portal.component.html',
  styleUrl: './user-portal.component.css'
})
export class UserPortalComponent implements OnInit{
  
  constructor(private router: Router) { }
  
  ngOnInit(): void {
  }

}
