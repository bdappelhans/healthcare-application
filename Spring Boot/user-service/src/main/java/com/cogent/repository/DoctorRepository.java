package com.cogent.repository;

import com.cogent.entity.Appointment;
import com.cogent.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialtyId(Long specialityId);

    List<Doctor> findByCityId(Long cityId);

    List<Doctor> findBySpecialtyIdAndCityId(Long specialityId, Long cityId);
}
