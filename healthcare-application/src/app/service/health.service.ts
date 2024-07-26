import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { State } from "../model/state";
import { Specialty } from "../model/specialty";
import { Doctor } from "../model/doctor";

@Injectable({
    providedIn: 'root'
  })
  export class HealthService {

    private healthApiUrl = 'http://localhost:8080/api/health';
  
    private httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
  
    constructor(private http: HttpClient) { }
  
    getSpecialties(): Observable<Specialty[]> {
        const url = `${this.healthApiUrl}/specialties`;
        return this.http.get<Specialty[]>(url);
    }

    getDoctorsByCityAndSpecialty(cityId: number, specialtyId: number): Observable<Doctor[]> {
        const url = `${this.healthApiUrl}/doctors/city:${cityId}/specialty:${specialtyId}`;
        return this.http.get<Doctor[]>(url);
    }
}