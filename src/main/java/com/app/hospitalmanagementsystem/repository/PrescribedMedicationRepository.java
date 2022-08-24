package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.PrescribedMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescribedMedicationRepository extends JpaRepository<PrescribedMedication, Long> {
}
