package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.MedicalServiceSystem;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.MedicalServiceSystemRepository;
import com.app.hospitalmanagementsystem.serviceinterface.MedicalServiceSystemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalServiceSystemService implements MedicalServiceSystemServiceInterface {

    private final MedicalServiceSystemRepository medicalServiceSystemRepository;

    @Autowired
    public MedicalServiceSystemService(MedicalServiceSystemRepository medicalServiceSystemRepository) {
        this.medicalServiceSystemRepository = medicalServiceSystemRepository;
    }

    @Override
    public List<MedicalServiceSystem> getAllMedicalServices() {
        return medicalServiceSystemRepository.findAll();
    }

    @Override
    public MedicalServiceSystem getMedicalServiceById(Long medicalServiceId) {
        return medicalServiceSystemRepository.findById(medicalServiceId)
                .orElseThrow(()-> new ResourceNotFoundException("Medical service with id " + medicalServiceId + " doesn't exist."));
    }

    @Override
    public MedicalServiceSystem addNewMedicalService(MedicalServiceSystem medicalService) {
        return medicalServiceSystemRepository.save(medicalService);
    }

    @Override
    public MedicalServiceSystem updateMedicalService(Long medicalServiceId, MedicalServiceSystem medicalServiceUpdatedDetails) {
        MedicalServiceSystem updatedMedicalService = medicalServiceSystemRepository.findById(medicalServiceId)
                .orElseThrow(()-> new ResourceNotFoundException("Medical service with id " + medicalServiceId + " doesn't exist."));
        updatedMedicalService.setName(medicalServiceUpdatedDetails.getName());
        updatedMedicalService.setDepartment(medicalServiceUpdatedDetails.getDepartment());
        updatedMedicalService.setPrice(medicalServiceUpdatedDetails.getPrice());
        return medicalServiceSystemRepository.save(updatedMedicalService);
    }

    @Override
    public void deleteMedicalService(Long medicalServiceId) {
        MedicalServiceSystem medicalService = medicalServiceSystemRepository.findById(medicalServiceId)
                .orElseThrow(()-> new ResourceNotFoundException("Medical service with id " + medicalServiceId + " doesn't exist."));
        medicalServiceSystemRepository.delete(medicalService);
    }
}
