package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.PatientMedicalHistory;
import com.app.hospitalmanagementsystem.service.PatientMedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/patient-medical-histories")
public class PatientMedicalHistoryController {

    private final PatientMedicalHistoryService patientMedicalHistoryService;

    @Autowired
    public PatientMedicalHistoryController(PatientMedicalHistoryService patientMedicalHistoryService) {
        this.patientMedicalHistoryService = patientMedicalHistoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('patient_medical_history:read')")
    public List<PatientMedicalHistory> getAllPatientMedicalHistories() {
        return patientMedicalHistoryService.getAllPatientMedicalHistories();
    }

    @GetMapping("{patientMedicalHistoryId}")
    @PreAuthorize("hasAuthority('patient_medical_history:read')")
    public ResponseEntity<PatientMedicalHistory> getPatientMedicalHistoryById(@PathVariable Long patientMedicalHistoryId) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryService.getPatientMedicalHistoryById(patientMedicalHistoryId);
        return ResponseEntity.ok(patientMedicalHistory);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('patient_medical_history:write')")
    public PatientMedicalHistory addNewPatientMedicalHistory(@RequestBody PatientMedicalHistory patientMedicalHistory) {
        return patientMedicalHistoryService.addNewPatientMedicalHistory(patientMedicalHistory);
    }

    @PutMapping("{patientMedicalHistoryId}")
    @PreAuthorize("hasAuthority('patient_medical_history:write')")
    public ResponseEntity<PatientMedicalHistory> updatePatientMedicalHistory(
            @PathVariable Long patientMedicalHistoryId, @RequestBody PatientMedicalHistory patientMedicalHistoryUpdatedDetails) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryService.updatePatientMedicalHistory(patientMedicalHistoryId, patientMedicalHistoryUpdatedDetails);
        return ResponseEntity.ok(patientMedicalHistory);
    }

    @DeleteMapping("{patientMedicalHistoryId}")
    @PreAuthorize("hasAuthority('patient_medical_history:write')")
    public ResponseEntity<PatientMedicalHistory> deletePatientMedicalHistory(@PathVariable Long patientMedicalHistoryId) {
        patientMedicalHistoryService.deletePatientMedicalHistory(patientMedicalHistoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{patientMedicalHistoryId}/patients/{patientId}")
    @PreAuthorize("hasAuthority('patient_medical_history:write') and hasAuthority('patient:read')")
    public PatientMedicalHistory connectPatientMedicalHistoryToPatient(@PathVariable Long patientMedicalHistoryId, @PathVariable Long patientId) {
        return patientMedicalHistoryService.connectPatientMedicalHistoryToPatient(patientMedicalHistoryId, patientId);
    }

    @PutMapping("{patientMedicalHistoryId}/doctors/{doctorId}")
    @PreAuthorize("hasAuthority('patient_medical_history:write') and hasAuthority('doctor:read')")
    public PatientMedicalHistory connectPatientMedicalHistoryToDoctor(@PathVariable Long patientMedicalHistoryId, @PathVariable Long doctorId) {
        return patientMedicalHistoryService.connectPatientMedicalHistoryToDoctor(patientMedicalHistoryId, doctorId);
    }

    @PutMapping("{patientMedicalHistoryId}/bills/{billId}")
    @PreAuthorize("hasAuthority('patient_medical_history:write') and hasAuthority('bill:read')")
    public PatientMedicalHistory addPatientMedicalHistoryToBill(@PathVariable Long patientMedicalHistoryId, @PathVariable Long billId) {
        return patientMedicalHistoryService.addPatientMedicalHistoryToBill(patientMedicalHistoryId, billId);
    }
}
