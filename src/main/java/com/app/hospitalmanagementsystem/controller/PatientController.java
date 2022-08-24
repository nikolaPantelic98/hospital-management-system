package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('patient:read')")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("{patientId}")
    @PreAuthorize("hasAuthority('patient:read')")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('patient:write')")
    public Patient registerNewPatient(@RequestBody Patient patient) {
        return patientService.registerNewPatient(patient);
    }

    @PutMapping("{patientId}")
    @PreAuthorize("hasAuthority('patient:write')")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long patientId, @RequestBody Patient patientUpdatedDetails) {
        Patient patient = patientService.updatePatient(patientId, patientUpdatedDetails);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("{patientId}")
    @PreAuthorize("hasAuthority('patient:write')")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
