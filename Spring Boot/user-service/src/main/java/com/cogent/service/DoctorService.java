package com.cogent.service;

import com.cogent.entity.Doctor;
import com.cogent.entity.Specialty;

import java.util.List;

public interface DoctorService {

    List<Specialty> getSpecialties();

    Specialty getSpecialtyById(Long id);

    List<Doctor> getDoctors();

    Doctor getDoctorById(Long id);

    List<Doctor> getDoctorsBySpecialtyId(Long id);

    List<Doctor> getDoctorsByCityId(Long cityId);

    List<Doctor> getDoctorsBySpecialtyIdAndCityId(Long specialtyId, Long cityId);
}
