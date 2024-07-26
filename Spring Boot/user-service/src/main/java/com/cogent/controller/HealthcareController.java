package com.cogent.controller;

import ch.qos.logback.classic.Logger;
import com.cogent.entity.Appointment;
import com.cogent.entity.Doctor;
import com.cogent.entity.Specialty;
import com.cogent.service.ApptService;
import com.cogent.service.DoctorService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthcareController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ApptService apptService;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(HealthcareController.class);

    @GetMapping("/specialties")
    public List<Specialty> getSpecialties() {
        return doctorService.getSpecialties();
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("/doctors/city:{cityId}/specialty:{specialtyId}")
    public List<Doctor> getDoctorsBySpecialty(@PathVariable Long cityId, @PathVariable Long specialtyId) {
        return doctorService.getDoctorsBySpecialtyIdAndCityId(specialtyId, cityId);
    }

    @GetMapping("/specialty/{id}")
    public Specialty getSpecialty(@PathVariable Long id) {
        return doctorService.getSpecialtyById(id);
    }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/appt/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return apptService.findById(id);
    }

    @GetMapping("/appts/{doctorId}/{apptDate}")
    public List<Appointment> getAppointmentByDoctor(
            @PathVariable Long doctorId,
            @PathVariable String apptDate) { // Temporarily change to String for debugging

        logger.info("Doctor ID: {}, Appointment Date (String): {}", doctorId, apptDate);

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(apptDate); // Manually parse for debugging
        } catch (DateTimeParseException e) {
            logger.error("Error parsing date: {}", e.getMessage());
            return Collections.emptyList();
        }

        logger.info("Parsed Appointment Date: {}", parsedDate);
        return apptService.findByDoctorIdAndApptDate(doctorId, parsedDate);
    }

    @GetMapping("/appts/{patientEmail}")
    public List<Appointment> getAppointmentByPatientEmail(@PathVariable String patientEmail) {
        return apptService.findByPatientEmail(patientEmail);
    }

    @PostMapping("/appt/add")
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return apptService.create(appointment);
    }

    @PutMapping("/appt/update")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return apptService.update(appointment);
    }

    @DeleteMapping("/appt/delete/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        apptService.deleteById(id);
    }
}
