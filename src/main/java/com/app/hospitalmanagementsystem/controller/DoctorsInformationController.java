package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.DoctorsInformation;
import com.app.hospitalmanagementsystem.service.DoctorsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/doctors-information")
public class DoctorsInformationController {

    private final DoctorsInformationService doctorsInformationService;

    @Autowired
    public DoctorsInformationController(DoctorsInformationService doctorsInformationService) {
        this.doctorsInformationService = doctorsInformationService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('doctors_information:read')")
    public List<DoctorsInformation> getAllDoctorsInformation() {
        return doctorsInformationService.getAllDoctorsInformation();
    }

    @GetMapping("{doctorsInformationId}")
    @PreAuthorize("hasAuthority('doctors_information:read')")
    public ResponseEntity<DoctorsInformation> getDoctorsInformationById(@PathVariable Long doctorsInformationId) {
        DoctorsInformation doctorsInformation = doctorsInformationService.getDoctorsInformationById(doctorsInformationId);
        return ResponseEntity.ok(doctorsInformation);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('doctors_information:write')")
    public DoctorsInformation addNewDoctorsInformation(@RequestBody DoctorsInformation doctorsInformation) {
        return doctorsInformationService.addNewDoctorsInformation(doctorsInformation);
    }

    @PutMapping("{doctorsInformationId}")
    @PreAuthorize("hasAuthority('doctors_information:write')")
    public ResponseEntity<DoctorsInformation> updateDoctorsInformation(
            @PathVariable Long doctorsInformationId, @RequestBody DoctorsInformation doctorsInformationUpdatedDetails) {
        DoctorsInformation doctorsInformation = doctorsInformationService.updateDoctorsInformation(doctorsInformationId, doctorsInformationUpdatedDetails);
        return ResponseEntity.ok(doctorsInformation);
    }

    @DeleteMapping("{doctorsInformationId}")
    @PreAuthorize("hasAuthority('doctors_information:write')")
    public ResponseEntity<DoctorsInformation> deleteDoctorsInformation(@PathVariable Long doctorsInformationId) {
        doctorsInformationService.deleteDoctorsInformation(doctorsInformationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
