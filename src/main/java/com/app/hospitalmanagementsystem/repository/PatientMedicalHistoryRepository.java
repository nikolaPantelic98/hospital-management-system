package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.PatientMedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMedicalHistoryRepository extends JpaRepository<PatientMedicalHistory, Long> {
}
