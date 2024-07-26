import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminPortalComponent } from './admin-portal/admin-portal.component';
import { AuthGuard } from './guards/auth.guard';
import { UserPortalComponent } from './user-portal/user-portal.component';
import { RegisterComponent } from './register/register.component';
import { PatientAppointmentListComponent } from './patient-appointment-list/patient-appointment-list.component';
import { NewAppointmentComponent } from './new-appointment/new-appointment.component';
import { NewAppointmentScheduleComponent } from './new-appointment-schedule/new-appointment-schedule.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'admin', component: AdminPortalComponent, canActivate: [AuthGuard], data: { admin: true }},
    { path: 'user', component: UserPortalComponent, canActivate: [AuthGuard], data: { admin: false }, children: [
        { path: 'appointment-list', component: PatientAppointmentListComponent },
        { path: 'new-appointment', component: NewAppointmentComponent },
        { path: 'new-appointment-schedule', component: NewAppointmentScheduleComponent },
        { path: '', redirectTo: 'appointment-list', pathMatch: 'full' }
    ]},
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/login' }
];
