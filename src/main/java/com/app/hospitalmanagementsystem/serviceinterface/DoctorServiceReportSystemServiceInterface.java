package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.DoctorServiceReportSystem;

import java.util.List;

public interface DoctorServiceReportSystemServiceInterface {

    List<DoctorServiceReportSystem> getAllDoctorServiceReports();
    DoctorServiceReportSystem getDoctorServiceReportById(Long doctorServiceReportId);
    DoctorServiceReportSystem addNewDoctorServiceReport(DoctorServiceReportSystem doctorServiceReport);
    DoctorServiceReportSystem updateDoctorServiceReport(
            Long doctorServiceReportId, DoctorServiceReportSystem doctorServiceReportUpdatedDetails);
    void deleteDoctorServiceReport(Long doctorServiceReportId);
    DoctorServiceReportSystem addDoctorServiceReportToDoctor(Long doctorServiceReportId, Long doctorId);
}
