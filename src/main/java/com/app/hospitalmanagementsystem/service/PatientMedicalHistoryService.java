package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Bill;
import com.app.hospitalmanagementsystem.entity.Doctor;
import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.entity.PatientMedicalHistory;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.BillRepository;
import com.app.hospitalmanagementsystem.repository.DoctorRepository;
import com.app.hospitalmanagementsystem.repository.PatientMedicalHistoryRepository;
import com.app.hospitalmanagementsystem.repository.PatientRepository;
import com.app.hospitalmanagementsystem.serviceinterface.PatientMedicalHistoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMedicalHistoryService implements PatientMedicalHistoryServiceInterface {

    private final PatientMedicalHistoryRepository patientMedicalHistoryRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final BillRepository billRepository;

    @Autowired
    public PatientMedicalHistoryService(PatientMedicalHistoryRepository patientMedicalHistoryRepository, PatientRepository patientRepository,
                                        DoctorRepository doctorRepository, BillRepository billRepository) {
        this.patientMedicalHistoryRepository = patientMedicalHistoryRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.billRepository = billRepository;
    }

    @Override
    public List<PatientMedicalHistory> getAllPatientMedicalHistories() {
        return patientMedicalHistoryRepository.findAll();
    }

    @Override
    public PatientMedicalHistory getPatientMedicalHistoryById(Long patientMedicalHistoryId) {
        return patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
    }

    @Override
    public PatientMedicalHistory addNewPatientMedicalHistory(PatientMedicalHistory patientMedicalHistory) {
        return patientMedicalHistoryRepository.save(patientMedicalHistory);
    }

    @Override
    public PatientMedicalHistory updatePatientMedicalHistory(Long patientMedicalHistoryId, PatientMedicalHistory patientMedicalHistoryUpdatedDetails) {
        PatientMedicalHistory updatedPatientMedicalHistory = patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
        updatedPatientMedicalHistory.setDateOfTreatment(patientMedicalHistoryUpdatedDetails.getDateOfTreatment());
        updatedPatientMedicalHistory.setTreatmentDescription(patientMedicalHistoryUpdatedDetails.getTreatmentDescription());
        return patientMedicalHistoryRepository.save(updatedPatientMedicalHistory);
    }

    @Override
    public void deletePatientMedicalHistory(Long patientMedicalHistoryId) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
        patientMedicalHistoryRepository.delete(patientMedicalHistory);
    }

    @Override
    public PatientMedicalHistory connectPatientMedicalHistoryToPatient(Long patientMedicalHistoryId, Long patientId) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
        patientMedicalHistory.connectToPatient(patient);
        return patientMedicalHistoryRepository.save(patientMedicalHistory);
    }

    @Override
    public PatientMedicalHistory connectPatientMedicalHistoryToDoctor(Long patientMedicalHistoryId, Long doctorId) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor with id " + doctorId + " doesn't exist."));
        patientMedicalHistory.connectToDoctor(doctor);
        return patientMedicalHistoryRepository.save(patientMedicalHistory);

    }

    @Override
    public PatientMedicalHistory addPatientMedicalHistoryToBill(Long patientMedicalHistoryId, Long billId) {
        PatientMedicalHistory patientMedicalHistory = patientMedicalHistoryRepository.findById(patientMedicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient medical history with id " + patientMedicalHistoryId + " doesn't exist."));
        Bill bill = billRepository.findById(billId)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " + billId + " doesn't exist."));
        patientMedicalHistory.addToBill(bill);
        return patientMedicalHistoryRepository.save(patientMedicalHistory);
    }
}
