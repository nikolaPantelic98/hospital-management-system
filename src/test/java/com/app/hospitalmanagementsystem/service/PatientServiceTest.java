package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void shouldGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());

        given(patientRepository.findAll()).willReturn(patients);

        List<Patient> expectedPatients = patientService.getAllPatients();

        Assertions.assertEquals(expectedPatients, patients);
        verify(patientRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnPatient_ifFound() {
        Patient patient =
                Patient.builder()
                        .patientId(1L)
                        .fullName("James Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));

        Patient expectedPatient = patientService.getPatientById(patient.getPatientId());

        assertThat(expectedPatient).isSameAs(patient);
        verify(patientRepository).findById(patient.getPatientId());
    }

    @Test
    void shouldThrowException_whenPatientDoesntExist_whileReturningPatient() {
        Patient patient =
                Patient.builder()
                        .patientId(1L)
                        .fullName("James Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        given(patientRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientService.getPatientById(patient.getPatientId()));
    }

    @Test
    void whenSavePatient_shouldReturnPatient() {
        Patient patient =
                Patient.builder()
                        .fullName("James Cooler")
                        .emailAddress("jamescooler96@gmail.com")
                        .build();

        when(patientRepository.save(ArgumentMatchers.any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.registerNewPatient(patient);

        assertThat(createdPatient.getFullName()).isSameAs(patient.getFullName());
        assertThat(createdPatient.getEmailAddress()).isSameAs(patient.getEmailAddress());
        verify(patientRepository).save(patient);
    }

    @Test
    void whenGivenId_shouldUpdatePatient_ifFound() {
        Patient patient =
                Patient.builder()
                        .patientId(10L)
                        .fullName("James Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        Patient newPatient =
                Patient.builder()
                        .emailAddress("jamescooler98@gmail.com")
                        .build();

        given(patientRepository.findById(patient.getPatientId())).willReturn(Optional.of(patient));
        patientService.updatePatient(patient.getPatientId(), newPatient);

        verify(patientRepository).save(patient);
        verify(patientRepository).findById(patient.getPatientId());
    }

    @Test
    void shouldThrowException_whenPatientDoesntExist_whileUpdatingPatient() {
        Patient patient =
                Patient.builder()
                        .patientId(10L)
                        .fullName("James Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        Patient newPatient =
                Patient.builder()
                        .patientId(11L)
                        .emailAddress("jamescooler98@gmail.com")
                        .build();

        given(patientRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientService.updatePatient(patient.getPatientId(), newPatient));
    }

    @Test
    void whenGivenId_shouldDeletePatient_ifFound() {
        Patient patient =
                Patient.builder()
                        .patientId(20L)
                        .fullName("Jimmy Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));

        patientService.deletePatient(patient.getPatientId());
        verify(patientRepository).delete(patient);
    }

    @Test
    void shouldThrowException_whenPatientDoesntExist_whileDeletingPatient() {
        Patient patient =
                Patient.builder()
                        .patientId(21L)
                        .fullName("Jimmy Cooler")
                        .emailAddress("jamescooler@gmail.com")
                        .build();

        given(patientRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientService.deletePatient(patient.getPatientId()));
    }
}