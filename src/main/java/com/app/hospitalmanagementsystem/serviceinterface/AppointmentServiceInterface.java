package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.Appointment;

import java.util.List;

public interface AppointmentServiceInterface {

    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long appointmentId);
    Appointment createNewAppointment(Appointment appointment);
    Appointment updateAppointment(Long appointmentId, Appointment appointmentUpdatedDetails);
    void deleteAppointment(Long appointmentId);
    Appointment makeAppointmentWithPatient(Long appointmentId, Long patientId);
}
