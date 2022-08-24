package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.Doctor;
import com.app.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('doctor:read')")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("{doctorId}")
    @PreAuthorize("hasAuthority('doctor:read')")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('doctor:write')")
    public Doctor registerNewDoctor(@RequestBody Doctor doctor) {
        return doctorService.registerNewDoctor(doctor);
    }

    @PutMapping("{doctorId}")
    @PreAuthorize("hasAuthority('doctor:write')")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctorUpdatedDetails) {
        Doctor doctor = doctorService.updateDoctor(doctorId, doctorUpdatedDetails);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("{doctorId}")
    @PreAuthorize("hasAuthority('doctor:write')")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
