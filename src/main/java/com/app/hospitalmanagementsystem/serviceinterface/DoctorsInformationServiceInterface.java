package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.DoctorsInformation;

import java.util.List;

public interface DoctorsInformationServiceInterface {

    List<DoctorsInformation> getAllDoctorsInformation();
    DoctorsInformation getDoctorsInformationById(Long doctorsInformationId);
    DoctorsInformation addNewDoctorsInformation(DoctorsInformation doctorsInformation);
    DoctorsInformation updateDoctorsInformation(
            Long doctorInformationId, DoctorsInformation doctorInformationUpdatedDetails);
    void deleteDoctorsInformation(Long doctorsInformationId);
}
