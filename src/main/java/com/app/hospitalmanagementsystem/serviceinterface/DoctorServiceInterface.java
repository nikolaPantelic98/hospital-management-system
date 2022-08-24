package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.Doctor;

import java.util.List;

public interface DoctorServiceInterface {

    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long doctorId);
    Doctor registerNewDoctor(Doctor doctor);
    Doctor updateDoctor(Long doctorId, Doctor doctorUpdatedDetails);
    void deleteDoctor(Long doctorId);
}
