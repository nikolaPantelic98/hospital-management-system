package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Appointment;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class  AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void shouldGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());

        given(appointmentRepository.findAll()).willReturn(appointments);

        List<Appointment> expectedAppointments = appointmentService.getAllAppointments();

        Assertions.assertEquals(expectedAppointments, appointments);
        verify(appointmentRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnAppointment_ifFound() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(1L)
                        .build();

        when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));

        Appointment expectedAppointment = appointmentService.getAppointmentById(appointment.getAppointmentId());

        assertThat(expectedAppointment).isSameAs(appointment);
        verify(appointmentRepository).findById(appointment.getAppointmentId());
    }

    @Test
    void shouldThrowException_whenAppointmentDoesntExist_whileReturningAppointment() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(1L)
                        .dateOfAppointment(LocalDate.of(2020, Month.JANUARY, 19))
                        .build();

        given(appointmentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                appointmentService.getAppointmentById(appointment.getAppointmentId()));
    }

    @Test
    void whenSaveAppointment_shouldReturnAppointment() {
        Appointment appointment =
                Appointment.builder()
                        .dateOfAppointment(LocalDate.of(2020, Month.OCTOBER, 18))
                        .build();

        when(appointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.createNewAppointment(appointment);

        assertThat(createdAppointment.getDateOfAppointment()).isSameAs(appointment.getDateOfAppointment());
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void whenGivenId_shouldUpdateAppointment_ifFound() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(10L)
                        .dateOfAppointment(LocalDate.of(2021, Month.DECEMBER, 28))
                        .build();

        Appointment newAppointment =
                Appointment.builder()
                        .dateOfAppointment(LocalDate.of(2019, Month.DECEMBER, 18))
                        .build();

        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.of(appointment));
        appointmentService.updateAppointment(appointment.getAppointmentId(), newAppointment);

        verify(appointmentRepository).save(appointment);
        verify(appointmentRepository).findById(appointment.getAppointmentId());
    }

    @Test
    void shouldThrowException_whenAppointmentDoesntExist_whileUpdatingAppointment() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(10L)
                        .dateOfAppointment(LocalDate.of(2021, Month.DECEMBER, 28))
                        .build();

        Appointment newAppointment =
                Appointment.builder()
                        .appointmentId(11L)
                        .dateOfAppointment(LocalDate.of(2019, Month.DECEMBER, 18))
                        .build();

        given(appointmentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                appointmentService.updateAppointment(appointment.getAppointmentId(), newAppointment));
    }

    @Test
    void whenGivenId_shouldDeleteAppointment_ifFound() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(20L)
                        .dateOfAppointment(LocalDate.of(2019, Month.APRIL, 20))
                        .build();

        when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));

        appointmentService.deleteAppointment(appointment.getAppointmentId());
        verify(appointmentRepository).delete(appointment);
    }

    @Test
    void shouldThrowException_whenAppointmentDoesntExist_whileDeletingAppointment() {
        Appointment appointment =
                Appointment.builder()
                        .appointmentId(21L)
                        .dateOfAppointment(LocalDate.of(2019, Month.APRIL, 20))
                        .build();

        given(appointmentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                appointmentService.deleteAppointment(appointment.getAppointmentId()));
    }
}