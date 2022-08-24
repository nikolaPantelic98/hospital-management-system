package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.Patient;

import java.util.List;

public interface PatientServiceInterface {

    List<Patient> getAllPatients();
    Patient getPatientById(Long patientId);
    Patient registerNewPatient(Patient patient);
    Patient updatePatient(Long patientId, Patient patientUpdatedDetails);
    void deletePatient(Long patientId);
}
