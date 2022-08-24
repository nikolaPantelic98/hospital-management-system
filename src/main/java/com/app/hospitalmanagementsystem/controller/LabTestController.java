package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.LabTest;
import com.app.hospitalmanagementsystem.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/lab-tests")
public class LabTestController {

    private final LabTestService labTestService;

    @Autowired
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('lab_test:read')")
    public List<LabTest> getAllLabTests() {
        return labTestService.getAllLabTests();
    }

    @GetMapping("{labTestId}")
    @PreAuthorize("hasAuthority('lab_test:read')")
    public ResponseEntity<LabTest> getLabTestById(@PathVariable Long labTestId) {
        LabTest labTest = labTestService.getLabTestById(labTestId);
        return ResponseEntity.ok(labTest);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('lab_test:write')")
    public LabTest addNewLabTest(@RequestBody LabTest labTest) {
        return labTestService.addNewLabTest(labTest);
    }

    @PutMapping("{labTestId}")
    @PreAuthorize("hasAuthority('lab_test:write')")
    public ResponseEntity<LabTest> updateLabTest(@PathVariable Long labTestId, @RequestBody LabTest labTestUpdatedDetails) {
        LabTest labTest = labTestService.updateLabTest(labTestId, labTestUpdatedDetails);
        return ResponseEntity.ok(labTest);
    }

    @DeleteMapping("{labTestId}")
    @PreAuthorize("hasAuthority('lab_test:write')")
    public ResponseEntity<LabTest> deleteLabTest(@PathVariable Long labTestId) {
        labTestService.deleteLabTest(labTestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{labTestId}/patients/{patientId}")
    @PreAuthorize("hasAuthority('lab_test:write') and hasAuthority('patient:read')")
    public LabTest connectLabTestToPatient(@PathVariable Long labTestId, @PathVariable Long patientId) {
        return labTestService.connectLabTestToPatient(labTestId, patientId);
    }
}
