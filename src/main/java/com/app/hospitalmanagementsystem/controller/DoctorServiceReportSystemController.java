package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.DoctorServiceReportSystem;
import com.app.hospitalmanagementsystem.service.DoctorServiceReportSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/doctor-service-reports")
public class DoctorServiceReportSystemController {

    private final DoctorServiceReportSystemService doctorServiceReportSystemService;

    @Autowired
    public DoctorServiceReportSystemController(DoctorServiceReportSystemService doctorServiceReportSystemService) {
        this.doctorServiceReportSystemService = doctorServiceReportSystemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('doctor_service_report_system:read')")
    public List<DoctorServiceReportSystem> getAllDoctorServiceReports() {
        return doctorServiceReportSystemService.getAllDoctorServiceReports();
    }

    @GetMapping("{doctorServiceReportId}")
    @PreAuthorize("hasAuthority('doctor_service_report_system:read')")
    public ResponseEntity<DoctorServiceReportSystem> getDoctorServiceReportById(@PathVariable Long doctorServiceReportId) {
        DoctorServiceReportSystem doctorServiceReport = doctorServiceReportSystemService.getDoctorServiceReportById(doctorServiceReportId);
        return ResponseEntity.ok(doctorServiceReport);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('doctor_service_report_system:write')")
    public DoctorServiceReportSystem addNewDoctorServiceReport(@RequestBody DoctorServiceReportSystem doctorServiceReport) {
        return doctorServiceReportSystemService.addNewDoctorServiceReport(doctorServiceReport);
    }

    @PutMapping("{doctorServiceReportId}")
    @PreAuthorize("hasAuthority('doctor_service_report_system:write')")
    public ResponseEntity<DoctorServiceReportSystem> updateDoctorServiceReport(
            @PathVariable Long doctorServiceReportId, @RequestBody DoctorServiceReportSystem doctorServiceReportUpdatedDetails) {
        DoctorServiceReportSystem doctorServiceReport = doctorServiceReportSystemService.updateDoctorServiceReport(doctorServiceReportId, doctorServiceReportUpdatedDetails);
        return ResponseEntity.ok(doctorServiceReport);
    }

    @DeleteMapping("{doctorServiceReportId}")
    @PreAuthorize("hasAuthority('doctor_service_report_system:write')")
    public ResponseEntity<DoctorServiceReportSystem> deleteDoctorServiceReport(@PathVariable Long doctorServiceReportId) {
        doctorServiceReportSystemService.deleteDoctorServiceReport(doctorServiceReportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{doctorServiceReportId}/doctors{doctorId}")
    @PreAuthorize("hasAuthority('doctor_service_report_system:write') and hasAuthority('doctor:read')")
    public DoctorServiceReportSystem addDoctorServiceReportToDoctor(@PathVariable Long doctorServiceReportId, @PathVariable Long doctorId) {
        return doctorServiceReportSystemService.addDoctorServiceReportToDoctor(doctorServiceReportId, doctorId);
    }
}
