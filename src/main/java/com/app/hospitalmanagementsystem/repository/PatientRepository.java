package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
