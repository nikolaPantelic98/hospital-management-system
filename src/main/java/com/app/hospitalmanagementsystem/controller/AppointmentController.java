package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.Appointment;
import com.app.hospitalmanagementsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('appointment:read')")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("{appointmentId}")
    @PreAuthorize("hasAuthority('appointment:read')")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('appointment:write')")
    public Appointment createNewAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createNewAppointment(appointment);
    }

    @PutMapping("{appointmentId}")
    @PreAuthorize("hasAuthority('appointment:write')")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long appointmentId, @RequestBody Appointment appointmentUpdatedDetails) {
        Appointment appointment = appointmentService.updateAppointment(appointmentId, appointmentUpdatedDetails);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("{appointmentId}")
    @PreAuthorize("hasAuthority('appointment:write')")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{appointmentId}/patients/{patientId}")
    @PreAuthorize("hasAuthority('appointment:write') and hasAuthority('patient:read')")
    public Appointment makeAppointmentWithPatient(@PathVariable Long appointmentId, @PathVariable Long patientId) {
        return appointmentService.makeAppointmentWithPatient(appointmentId, patientId);
    }
}
