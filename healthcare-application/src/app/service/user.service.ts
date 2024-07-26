import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../model/user";


@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    private userApiUrl = 'http://localhost:8080/api/users';
  
    private httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
  
    constructor(private http: HttpClient) { }
  
    getUserByEmail(email: string) {
      const url = `${this.userApiUrl}/${email}`;
      return this.http.get<User>(url);
    }

    getAllUsers() {
      const url = `${this.userApiUrl}/all`;
      return this.http.get<User[]>(url);
    }

    addUser(user: User) {
      const url = `${this.userApiUrl}/add`;
      return this.http.post<User>(url, user, this.httpOptions);
    }
  }