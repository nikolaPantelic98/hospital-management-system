package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.PrescribedMedication;

import java.util.List;

public interface PrescribedMedicationServiceInterface {

    List<PrescribedMedication> getAllPrescribedMedications();
    PrescribedMedication getPrescribedMedicationById(Long prescribedMedicationId);
    PrescribedMedication addNewPrescribedMedication(PrescribedMedication prescribedMedication);
    PrescribedMedication updatePrescribedMedication(
            Long prescribedMedicationId, PrescribedMedication prescribedMedicationUpdatedDetails);
    void deletePrescribedMedication(Long prescribedMedicationId);
    PrescribedMedication addPrescribedMedicationToPatient(Long prescribedMedicationId, Long patientId);
    PrescribedMedication addPrescribedMedicationToDoctor(Long prescribedMedicationId, Long doctorId);
    PrescribedMedication addPrescribedMedicationToDrugsInventory(Long prescribedMedicationId, Long drugId);
    PrescribedMedication addPrescribedMedicationToInventory(Long prescribedMedicationId, Long inventoryId);
}
