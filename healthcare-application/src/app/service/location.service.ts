import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { State } from "../model/state";

@Injectable({
    providedIn: 'root'
  })
  export class LocationService {

    private locationApiUrl = 'http://localhost:8080/api/location';
  
    private httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
  
    constructor(private http: HttpClient) { }
  
    getStates(): Observable<State[]> {
        const url = `${this.locationApiUrl}/states`;
        return this.http.get<State[]>(url);
    }
}
