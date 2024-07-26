export class Time {
    constructor(
        public hour: number,
        public minutes: number,
        public displayHour: number,
        public period: string
    ) {}

    getDisplayTime(): string {
        return this.displayHour + ":" + this.minutes + " " + this.period;
    }
}