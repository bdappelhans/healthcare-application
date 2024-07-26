package com.cogent.service;

import com.cogent.entity.Doctor;
import com.cogent.entity.Specialty;
import com.cogent.entity.State;
import com.cogent.repository.DoctorRepository;
import com.cogent.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Specialty> getSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public Specialty getSpecialtyById(Long id) {
        Optional<Specialty> result = specialtyRepository.findById(id);
        Specialty specialty = null;

        if (result.isPresent()) {
            specialty = result.get();
        } else {
            throw new RuntimeException("Did not find specialty with ID " + id);
        }
        return specialty;
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> result = doctorRepository.findById(id);
        Doctor doctor = null;

        if (result.isPresent()) {
            doctor = result.get();
        } else {
            throw new RuntimeException("Did not find doctor with ID " + id);
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialtyId(Long id) {
        return List.of();
    }

    @Override
    public List<Doctor> getDoctorsByCityId(Long cityId) {
        return List.of();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialtyIdAndCityId(Long specialtyId, Long cityId) {
        return doctorRepository.findBySpecialtyIdAndCityId(specialtyId, cityId);
    }
}
