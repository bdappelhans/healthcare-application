import { City } from "./city";
import { Specialty } from "./specialty";

export interface Doctor {
    id: number,
    firstName: string,
    lastName: string,
    rating: number,
    specialty: Specialty,
    city: City,
}