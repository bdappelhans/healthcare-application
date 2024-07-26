import { Doctor } from "./doctor";

export interface Specialty {
    id: number,
    name: string,
    doctors: Doctor[]
}