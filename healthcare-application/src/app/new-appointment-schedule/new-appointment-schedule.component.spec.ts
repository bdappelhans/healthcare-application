import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAppointmentScheduleComponent } from './new-appointment-schedule.component';

describe('NewAppointmentScheduleComponent', () => {
  let component: NewAppointmentScheduleComponent;
  let fixture: ComponentFixture<NewAppointmentScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewAppointmentScheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewAppointmentScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
