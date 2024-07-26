package com.cogent.service;

import com.cogent.entity.Appointment;
import com.cogent.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApptServiceImpl implements ApptService {

    @Autowired
    private AppointmentRepository apptRepository;

    @Override
    public Appointment findById(Long id) {
        return apptRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> findByDoctorIdAndApptDate(Long doctorId, LocalDate date) {
        List<Appointment> appointments = apptRepository.findByDoctorIdAndApptDate(doctorId, date);

        // Sort appointments by start time
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getStartTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByPatientEmail(String email) {
        List<Appointment> appointments = apptRepository.findByPatientEmail(email);

        // Sort appointments by date and then by start time
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getApptDate)
                        .thenComparing(Appointment::getStartTime))
                .collect(Collectors.toList());
    }

    @Override
    public Appointment create(Appointment appointment) {
        appointment.setId(0L);
        return apptRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return apptRepository.save(appointment);
    }

    @Override
    public void deleteById(Long id) {
        apptRepository.deleteById(id);
    }

}
