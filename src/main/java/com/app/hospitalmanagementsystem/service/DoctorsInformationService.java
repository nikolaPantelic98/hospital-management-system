package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.DoctorsInformation;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.DoctorsInformationRepository;
import com.app.hospitalmanagementsystem.serviceinterface.DoctorsInformationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorsInformationService implements DoctorsInformationServiceInterface {

    private final DoctorsInformationRepository doctorsInformationRepository;

    @Autowired
    public DoctorsInformationService(DoctorsInformationRepository doctorsInformationRepository) {
        this.doctorsInformationRepository = doctorsInformationRepository;
    }

    @Override
    public List<DoctorsInformation> getAllDoctorsInformation() {
        return doctorsInformationRepository.findAll();
    }

    @Override
    public DoctorsInformation getDoctorsInformationById(Long doctorsInformationId) {
        return doctorsInformationRepository.findById(doctorsInformationId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor's information with id " + doctorsInformationId + " doesn't exist."));
    }

    @Override
    public DoctorsInformation addNewDoctorsInformation(DoctorsInformation doctorsInformation) {
        return doctorsInformationRepository.save(doctorsInformation);
    }

    @Override
    public DoctorsInformation updateDoctorsInformation(Long doctorInformationId, DoctorsInformation doctorInformationUpdatedDetails) {
        DoctorsInformation updatedDoctorsInformation = doctorsInformationRepository.findById(doctorInformationId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor's infromation with id " + doctorInformationId + " doesn't exist."));
        updatedDoctorsInformation.setFullName(doctorInformationUpdatedDetails.getFullName());
        updatedDoctorsInformation.setDateOfBirth(doctorInformationUpdatedDetails.getDateOfBirth());
        updatedDoctorsInformation.setEmailAddress(doctorInformationUpdatedDetails.getEmailAddress());
        return doctorsInformationRepository.save(updatedDoctorsInformation);
    }

    @Override
    public void deleteDoctorsInformation(Long doctorsInformationId) {
        DoctorsInformation doctorsInformation = doctorsInformationRepository.findById(doctorsInformationId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor's information with id " + doctorsInformationId + " doesn't exist."));
        doctorsInformationRepository.delete(doctorsInformation);
    }
}
