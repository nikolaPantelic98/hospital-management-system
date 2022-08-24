package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.MedicalServiceSystem;
import com.app.hospitalmanagementsystem.service.MedicalServiceSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/medical-services")
public class MedicalServiceSystemController {

    private final MedicalServiceSystemService medicalServiceSystemService;

    @Autowired
    public MedicalServiceSystemController(MedicalServiceSystemService medicalServiceSystemService) {
        this.medicalServiceSystemService = medicalServiceSystemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('medical_service_system:read')")
    public List<MedicalServiceSystem> getAllMedicalServices() {
        return medicalServiceSystemService.getAllMedicalServices();
    }

    @GetMapping("{medicalServiceId}")
    @PreAuthorize("hasAuthority('medical_service_system:read')")
    public ResponseEntity<MedicalServiceSystem> getMedicalServiceById(@PathVariable Long medicalServiceId) {
        MedicalServiceSystem medicalService = medicalServiceSystemService.getMedicalServiceById(medicalServiceId);
        return ResponseEntity.ok(medicalService);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('medical_service_system:write')")
    public MedicalServiceSystem addNewMedicalService(@RequestBody MedicalServiceSystem medicalService) {
        return medicalServiceSystemService.addNewMedicalService(medicalService);
    }

    @PutMapping("{medicalServiceId}")
    @PreAuthorize("hasAuthority('medical_service_system:write')")
    public ResponseEntity<MedicalServiceSystem> updateMedicalService(
            @PathVariable Long medicalServiceId, @RequestBody MedicalServiceSystem medicalServiceUpdatedDetails) {
        MedicalServiceSystem medicalService = medicalServiceSystemService.updateMedicalService(medicalServiceId, medicalServiceUpdatedDetails);
        return ResponseEntity.ok(medicalService);
    }

    @DeleteMapping("{medicalServiceId}")
    @PreAuthorize("hasAuthority('medical_service_system:write')")
    public ResponseEntity<MedicalServiceSystem> deleteMedicalService(@PathVariable Long medicalServiceId) {
        medicalServiceSystemService.deleteMedicalService(medicalServiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
