package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.*;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.*;
import com.app.hospitalmanagementsystem.serviceinterface.PrescribedMedicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescribedMedicationService implements PrescribedMedicationServiceInterface {

    private final PrescribedMedicationRepository prescribedMedicationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DrugsInventoryRepository drugsInventoryRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public PrescribedMedicationService(PrescribedMedicationRepository prescribedMedicationRepository, PatientRepository patientRepository,
                                       DoctorRepository doctorRepository, DrugsInventoryRepository drugsInventoryRepository,
                                       InventoryRepository inventoryRepository) {
        this.prescribedMedicationRepository = prescribedMedicationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.drugsInventoryRepository = drugsInventoryRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<PrescribedMedication> getAllPrescribedMedications() {
        return prescribedMedicationRepository.findAll();
    }

    @Override
    public PrescribedMedication getPrescribedMedicationById(Long prescribedMedicationId) {
        return prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
    }

    @Override
    public PrescribedMedication addNewPrescribedMedication(PrescribedMedication prescribedMedication) {
        return prescribedMedicationRepository.save(prescribedMedication);
    }

    @Override
    public PrescribedMedication updatePrescribedMedication(Long prescribedMedicationId, PrescribedMedication prescribedMedicationUpdatedDetails) {
        PrescribedMedication updatedPrescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        updatedPrescribedMedication.setDateWhenMedicineWasPrescribed(prescribedMedicationUpdatedDetails.getDateWhenMedicineWasPrescribed());
        return prescribedMedicationRepository.save(updatedPrescribedMedication);
    }

    @Override
    public void deletePrescribedMedication(Long prescribedMedicationId) {
        PrescribedMedication prescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        prescribedMedicationRepository.delete(prescribedMedication);
    }

    @Override
    public PrescribedMedication addPrescribedMedicationToPatient(Long prescribedMedicationId, Long patientId) {
        PrescribedMedication prescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
        prescribedMedication.addToPatient(patient);
        return prescribedMedicationRepository.save(prescribedMedication);
    }

    @Override
    public PrescribedMedication addPrescribedMedicationToDoctor(Long prescribedMedicationId, Long doctorId) {
        PrescribedMedication prescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor with id " + doctorId + " doesn't exist."));
        prescribedMedication.addToDoctor(doctor);
        return prescribedMedicationRepository.save(prescribedMedication);
    }

    @Override
    public PrescribedMedication addPrescribedMedicationToDrugsInventory(Long prescribedMedicationId, Long drugId) {
        PrescribedMedication prescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        DrugsInventory drug = drugsInventoryRepository.findById(drugId)
                .orElseThrow(()-> new ResourceNotFoundException("Drug with id " + drugId + " doesn't exist."));
        prescribedMedication.addToDrugsInventory(drug);
        return prescribedMedicationRepository.save(prescribedMedication);
    }

    @Override
    public PrescribedMedication addPrescribedMedicationToInventory(Long prescribedMedicationId, Long inventoryId) {
        PrescribedMedication prescribedMedication = prescribedMedicationRepository.findById(prescribedMedicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Prescribed medication with id " + prescribedMedicationId + " doesn't exist."));
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Inventory with id " + inventoryId + " doesn't exist."));
        prescribedMedication.addToInventory(inventory);
        return prescribedMedicationRepository.save(prescribedMedication);
    }
}
