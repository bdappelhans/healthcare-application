import { Doctor } from "./doctor";
import { Time } from "./time";
import { User } from "./user";

export interface Appointment {
    id: number,
    doctor: Doctor,
    patient: User,
    apptDate: Date,
    startTime: Time,
    endTime: Time
}