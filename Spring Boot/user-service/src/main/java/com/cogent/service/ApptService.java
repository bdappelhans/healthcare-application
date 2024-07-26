package com.cogent.service;

import com.cogent.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface ApptService {

    Appointment findById(Long id);

    List<Appointment> findByDoctorIdAndApptDate(Long doctorId, LocalDate date);

    List<Appointment> findByPatientEmail(String email);

    Appointment create(Appointment appointment);

    Appointment update(Appointment appointment);

    void deleteById(Long id);
}
