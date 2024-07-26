import { Doctor } from "./doctor";
import { User } from "./user";

export interface Appointment {
    id: number,
    doctor: Doctor,
    patient: User,
    apptDate: string,
    startTime: string,
    endTime: string
}