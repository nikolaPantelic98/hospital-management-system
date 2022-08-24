package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
