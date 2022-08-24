package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.PatientRepository;
import com.app.hospitalmanagementsystem.serviceinterface.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
    }

    @Override
    public Patient registerNewPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long patientId, Patient patientUpdatedDetails) {
        Patient updatedPatient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist." ));
        updatedPatient.setFullName(patientUpdatedDetails.getFullName());
        updatedPatient.setDateOfBirth(patientUpdatedDetails.getDateOfBirth());
        updatedPatient.setAddress(patientUpdatedDetails.getAddress());
        updatedPatient.setIdNumber(patientUpdatedDetails.getIdNumber());
        updatedPatient.setPersonalIdentificationNumber(patientUpdatedDetails.getPersonalIdentificationNumber());
        updatedPatient.setEmailAddress(patientUpdatedDetails.getEmailAddress());
        return patientRepository.save(updatedPatient);
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
        patientRepository.delete(patient);
    }
}
