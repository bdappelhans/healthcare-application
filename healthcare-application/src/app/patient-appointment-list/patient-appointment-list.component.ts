import { CommonModule, } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { Appointment } from '../model/appointment';
import { ApptService } from '../service/appt.service';
import { User } from '../model/user';

@Component({
  selector: 'app-patient-appointment-list',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HttpClientModule, RouterModule],
  providers: [ApptService],
  templateUrl: './patient-appointment-list.component.html',
  styleUrl: './patient-appointment-list.component.css'
})
export class PatientAppointmentListComponent implements OnInit {

  appointments: Appointment[] = [];
  currentUser: User = {email: '', password: '', firstName: '', lastName: '', admin: false};

  constructor(private apptService: ApptService, private router: Router) { }

  ngOnInit(): void {
    if (typeof window !== 'undefined') {
      // Retrieve user details from localStorage
      const currentUserString = localStorage.getItem('user');
      if (currentUserString) {
        this.currentUser = JSON.parse(currentUserString);
        if (this.currentUser && this.currentUser.email) {
          this.fetchAppointments(this.currentUser.email);
        } else {
          this.router.navigate(['/login']);
        }
      } else {
        this.router.navigate(['/login']);
      }
    } else {
      console.error('localStorage is not available');
    }
  }

  fetchAppointments(email: string): void {
    this.apptService.getApptsByPatientEmail(email).subscribe(
      (appointments: Appointment[]) => {
        console.log('Appointments:', appointments);
        this.appointments = appointments;
      },
      (error) => {
        console.error('Error fetching appointments:', error);
      }
    );
  }

  
}
