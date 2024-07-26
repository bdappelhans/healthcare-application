import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, Router, ActivatedRoute, NavigationStart, NavigationEnd } from '@angular/router';
import { HealthService } from '../service/health.service';
import { ApptService } from '../service/appt.service';
import { Doctor } from '../model/doctor';
import { User } from '../model/user';
import { State } from '../model/state';
import { City } from '../model/city';
import { Specialty } from '../model/specialty';
import { Appointment } from '../model/appointment';
import { Time } from '../model/time';

@Component({
  selector: 'app-new-appointment-schedule',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterOutlet, RouterModule],
  providers: [HealthService, ApptService],
  templateUrl: './new-appointment-schedule.component.html',
  styleUrls: ['./new-appointment-schedule.component.css']
})
export class NewAppointmentScheduleComponent implements OnInit {
  currentUser: User = { email: '', password: '', firstName: '', lastName: '', admin: false };
  currentState: State = { id: 0, name: '', cities: [] };
  currentCity: City = { id: 0, name: '', state: this.currentState };
  currentSpecialty: Specialty = { id: 0, name: '', doctors: [] };
  currentDate: Date = new Date(2024, 6, 1);
  currentDoctor: Doctor = { id: 0, firstName: '', lastName: '', rating: 0, specialty: this.currentSpecialty, city: this.currentCity };
  apptsForDate: Appointment[] = [];
  appt: Appointment = { id: 0, doctor: this.currentDoctor, patient: this.currentUser, apptDate: this.currentDate,
    startTime: new Time(0, 0, 0, 'AM'), endTime: new Time(0, 0, 0, 'AM')
  }

  constructor(private router: Router) { }

  ngOnInit(): void {
    // Subscribe to router events to get navigation state

    if (typeof window !== 'undefined') {
      // Retrieve user details from localStorage
      const currentUserString = localStorage.getItem('user');
      const currentDoctorString = localStorage.getItem('currentDoctor');
      if (currentUserString && currentDoctorString) {
        this.currentUser = JSON.parse(currentUserString);
        this.currentDoctor = JSON.parse(currentDoctorString);
      } else {
        this.router.navigate(['/login']);
      }
    } else {
      console.error('localStorage is not available');
    }
    console.log('Current Doctor:', this.currentDoctor);
  }
}
