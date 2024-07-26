package com.cogent.repository;

import com.cogent.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdAndApptDate(Long doctorId, LocalDate apptDate);

    List<Appointment> findByPatientEmail(String patientEmail);

}
