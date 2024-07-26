import { HttpClient, HttpHeaders, HttpClientModule } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Appointment } from "../model/appointment";
import { Doctor } from "../model/doctor";
import { User } from "../model/user";
import { map } from 'rxjs/operators';
import { Time } from "../model/time";

interface AppointmentJSON {
    id: number,
    doctor: Doctor,
    patient: User,
    apptDate: string,
    startTime: string,
    endTime: string
}

@Injectable({
    providedIn: 'root'
  })
  export class ApptService {

    private userApiUrl = 'http://localhost:8080/api/health';
  
    private httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
  
    constructor(private http: HttpClient) { }
  
    getApptsByPatientEmail(email: string) {
      const url = `${this.userApiUrl}/appts/${email}`;
      return this.http.get<AppointmentJSON[]>(url).pipe(
        map(apptsJson => apptsJson.map(this.convertJsonToAppt.bind(this)))
    );
    }

    private convertJsonToAppt(apptJson: AppointmentJSON): Appointment {
        return {
            id: apptJson.id,
            doctor: apptJson.doctor,
            patient: apptJson.patient,
            apptDate: new Date(apptJson.apptDate),
            startTime: this.convertTimeStringToTime(apptJson.startTime),
            endTime: this.convertTimeStringToTime(apptJson.endTime)
        };
    }

    private convertTimeStringToTime(timeString: string): Time {
        const [hours, minutes] = timeString.split(':').map(Number);
        const period = hours >= 12 ? 'PM' : 'AM';
        const displayHour = hours % 12 || 12; // convert 0 hours to 12
        return new Time(hours, minutes, displayHour, period);
    }
  }