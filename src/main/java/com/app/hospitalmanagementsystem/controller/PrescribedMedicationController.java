package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.PrescribedMedication;
import com.app.hospitalmanagementsystem.service.PrescribedMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/prescribed-medications")
public class PrescribedMedicationController {

    private final PrescribedMedicationService prescribedMedicationService;

    @Autowired
    public PrescribedMedicationController(PrescribedMedicationService prescribedMedicationService) {
        this.prescribedMedicationService = prescribedMedicationService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('prescribed_medication:read')")
    public List<PrescribedMedication> getAllPrescribedMedications() {
        return prescribedMedicationService.getAllPrescribedMedications();
    }

    @GetMapping("{prescribedMedicationId}")
    @PreAuthorize("hasAuthority('prescribed_medication:read')")
    public ResponseEntity<PrescribedMedication> getPrescribedMedicationById(@PathVariable Long prescribedMedicationId) {
        PrescribedMedication prescribedMedication = prescribedMedicationService.getPrescribedMedicationById(prescribedMedicationId);
        return ResponseEntity.ok(prescribedMedication);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('prescribed_medication:write')")
    public PrescribedMedication addNewPrescribedMedication(@RequestBody PrescribedMedication prescribedMedication) {
        return prescribedMedicationService.addNewPrescribedMedication(prescribedMedication);
    }

    @PutMapping("{prescribedMedicationId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write')")
    public ResponseEntity<PrescribedMedication> updatePrescribedMedication(
            @PathVariable Long prescribedMedicationId, @RequestBody PrescribedMedication prescribedMedicationUpdatedDetails) {
        PrescribedMedication prescribedMedication = prescribedMedicationService.updatePrescribedMedication(prescribedMedicationId, prescribedMedicationUpdatedDetails);
        return ResponseEntity.ok(prescribedMedication);
    }

    @DeleteMapping("{prescribedMedicationId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write')")
    public ResponseEntity<PrescribedMedication> deletePrescribedMedication(@PathVariable Long prescribedMedicationId) {
        prescribedMedicationService.deletePrescribedMedication(prescribedMedicationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{prescribedMedicationId}/patients/{patientId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write') and hasAuthority('patient:read')")
    public PrescribedMedication addPrescribedMedicationToPatient(@PathVariable Long prescribedMedicationId, @PathVariable Long patientId) {
        return prescribedMedicationService.addPrescribedMedicationToPatient(prescribedMedicationId, patientId);
    }

    @PutMapping("{prescribedMedicationId}/doctors/{doctorId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write') and hasAuthority('doctor:read')")
    public PrescribedMedication addPrescribedMedicationToDoctor(@PathVariable Long prescribedMedicationId, @PathVariable Long doctorId) {
        return prescribedMedicationService.addPrescribedMedicationToDoctor(prescribedMedicationId, doctorId);
    }

    @PutMapping("{prescribedMedicationId}/drugs-inventories/{drugId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write') and hasAuthority('drugs_inventory:read')")
    public PrescribedMedication addPrescribedMedicationToDrugsInventory(@PathVariable Long prescribedMedicationId, @PathVariable Long drugId) {
        return prescribedMedicationService.addPrescribedMedicationToDrugsInventory(prescribedMedicationId, drugId);
    }

    @PutMapping("{prescribedMedicationId}/inventories{inventoryId}")
    @PreAuthorize("hasAuthority('prescribed_medication:write') and hasAuthority('inventory:read')")
    public PrescribedMedication addPrescribedMedicationToInventory(@PathVariable Long prescribedMedicationId, @PathVariable Long inventoryId) {
        return prescribedMedicationService.addPrescribedMedicationToInventory(prescribedMedicationId, inventoryId);
    }
}
