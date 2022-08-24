package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.LabTest;
import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.LabTestRepository;
import com.app.hospitalmanagementsystem.repository.PatientRepository;
import com.app.hospitalmanagementsystem.serviceinterface.LabTestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestService implements LabTestServiceInterface {

    private final LabTestRepository labTestRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public LabTestService(LabTestRepository labTestRepository, PatientRepository patientRepository) {
        this.labTestRepository = labTestRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }

    @Override
    public LabTest getLabTestById(Long labTestId) {
        return labTestRepository.findById(labTestId)
                .orElseThrow(()-> new ResourceNotFoundException("Lab test with id " + labTestId + " doesn't exist."));
    }

    @Override
    public LabTest addNewLabTest(LabTest labTest) {
        return labTestRepository.save(labTest);
    }

    @Override
    public LabTest updateLabTest(Long labTestId, LabTest labTestUpdatedDetails) {
        LabTest updatedLabTest = labTestRepository.findById(labTestId)
                .orElseThrow(()-> new ResourceNotFoundException("Lab test with id " + labTestId + " doesn't exist."));
        updatedLabTest.setName(labTestUpdatedDetails.getName());
        updatedLabTest.setTestDetailsDescription(labTestUpdatedDetails.getTestDetailsDescription());
        updatedLabTest.setDateWhenLabTestDone(labTestUpdatedDetails.getDateWhenLabTestDone());
        return labTestRepository.save(updatedLabTest);
    }

    @Override
    public void deleteLabTest(Long labTestId) {
        LabTest labTest = labTestRepository.findById(labTestId)
                .orElseThrow(()-> new ResourceNotFoundException("Lab test with id " + labTestId + " doesn't exist."));
        labTestRepository.delete(labTest);
    }

    @Override
    public LabTest connectLabTestToPatient(Long labTestId, Long patientId) {
        LabTest labTest = labTestRepository.findById(labTestId)
                .orElseThrow(()-> new ResourceNotFoundException("Lab test with id " + labTestId + " doesn't exist."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
        labTest.connectToPatient(patient);
        return labTestRepository.save(labTest);
    }
}
