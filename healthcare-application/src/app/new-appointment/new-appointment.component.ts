import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, Router } from '@angular/router';
import { ApptService } from '../service/appt.service';
import { User } from '../model/user';
import { State } from '../model/state';
import { City } from '../model/city';
import { LocationService } from '../service/location.service';
import { Specialty } from '../model/specialty';
import { HealthService } from '../service/health.service';
import { Doctor } from '../model/doctor';

@Component({
  selector: 'app-new-appointment',
  standalone: true,
  imports: [RouterModule, RouterOutlet, CommonModule, FormsModule, HttpClientModule],
  providers: [ApptService, LocationService, HealthService],
  templateUrl: './new-appointment.component.html',
  styleUrl: './new-appointment.component.css'
})
export class NewAppointmentComponent {
  currentUser: User = {email: '', password: '', firstName: '', lastName: '', admin: false};
  states: State[] = [];
  specialties: Specialty[] = [];
  doctors: Doctor[] = [];
  currentState: State = {id: 0, name: '', cities: []};
  currentCity: City = {id: 0, name: '', state: this.currentState};
  currentSpecialty: Specialty = {id: 0, name: '', doctors: []};
  currentDoctor: Doctor = { id: 0, firstName: '', lastName: '', rating: 0, specialty: this.currentSpecialty, city: this.currentCity }

  constructor(private apptService: ApptService, private locationService: LocationService, 
    private healthService: HealthService, private router: Router) { }

  ngOnInit(): void {
    if (typeof window !== 'undefined') {
      // Retrieve user details from localStorage
      const currentUserString = localStorage.getItem('user');
      if (currentUserString) {
        this.currentUser = JSON.parse(currentUserString);
      } else {
        this.router.navigate(['/login']);
      }
    } else {
      console.error('localStorage is not available');
    }

    this.fetchStates();
    this.fetchSpecialties();
  }

  fetchStates() {
    this.healthService.getSpecialties().subscribe(
      (specialties: Specialty[]) => {
        this.specialties = specialties;
      },
      (error) => {
        console.error('Error fetching specialties:', error);
      }
    );
  }

  fetchSpecialties() {
    this.locationService.getStates().subscribe(
      (states: State[]) => {
        this.states = states;
      },
      (error) => {
        console.error('Error fetching states:', error);
      }
    );
  }

  onStateChange(selectedState: State): void {
    this.currentState = selectedState;
    this.currentCity = {id: 0, name: '', state: selectedState};
    this.currentSpecialty = {id: 0, name: '', doctors: []};
    this.currentDoctor = { id: 0, firstName: '', lastName: '', rating: 0, specialty: this.currentSpecialty, city: this.currentCity };
  }

  onCityChange(selectedCity: City): void {
    this.currentCity = selectedCity;
    this.currentSpecialty = {id: 0, name: '', doctors: []};
    this.currentDoctor = { id: 0, firstName: '', lastName: '', rating: 0, specialty: this.currentSpecialty, city: this.currentCity };
  }

  onSpecialtyChange(selectedSpecialty: Specialty) {
    this.currentSpecialty = selectedSpecialty;
    this.currentDoctor = { id: 0, firstName: '', lastName: '', rating: 0, specialty: this.currentSpecialty, city: this.currentCity };
    console.log(this.currentSpecialty);

    this.healthService.getDoctorsByCityAndSpecialty(this.currentCity.id, this.currentSpecialty.id).subscribe(
      (doctors: Doctor[]) => {
        this.doctors = doctors;
      },
      (error) => {
        console.error('Error fetching doctors:', error);
      }
    );
  }

  onDoctorChange(selectedDoctor: Doctor) {
    this.currentDoctor = selectedDoctor;
    console.log(this.currentDoctor);
  }

  navigateToSchedule() {
    localStorage.setItem('currentDoctor', JSON.stringify(this.currentDoctor));
    this.router.navigate(['/user/new-appointment-schedule']);
  }
}
