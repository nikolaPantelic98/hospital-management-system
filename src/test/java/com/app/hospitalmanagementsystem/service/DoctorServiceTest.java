package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Doctor;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.DoctorRepository;
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
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    void shouldGetAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());

        given(doctorRepository.findAll()).willReturn(doctors);

        List<Doctor> expectedDoctors = doctorService.getAllDoctors();

        Assertions.assertEquals(expectedDoctors, doctors);
        verify(doctorRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnDoctor_ifFounded() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(1L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));

        Doctor expectedDoctor = doctorService.getDoctorById(doctor.getDoctorId());

        assertThat(expectedDoctor).isSameAs(doctor);
        verify(doctorRepository).findById(doctor.getDoctorId());
    }

    @Test
    void shouldThrowException_whenDoctorDoesntExist_whileReturningDoctor() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(1L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        given(doctorRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorService.getDoctorById(doctor.getDoctorId()));
    }

    @Test
    void whenSaveDoctor_shouldReturnDoctor() {
        Doctor doctor =
                Doctor.builder()
                        .fullName("Mark Black")
                        .emailAddress("markblack@gmail.com")
                        .build();

        when(doctorRepository.save(ArgumentMatchers.any(Doctor.class))).thenReturn(doctor);

        Doctor createdDoctor = doctorService.registerNewDoctor(doctor);

        assertThat(createdDoctor.getFullName()).isSameAs(doctor.getFullName());
        assertThat(createdDoctor.getEmailAddress()).isSameAs(doctor.getEmailAddress());
        verify(doctorRepository).save(doctor);
    }

    @Test
    void whenGivenId_shouldUpdateDoctor_ifFound() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(10L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        Doctor newDoctor =
                Doctor.builder()
                        .emailAddress("markwhite71@gmail.com")
                        .build();

        given(doctorRepository.findById(doctor.getDoctorId())).willReturn(Optional.of(doctor));
        doctorService.updateDoctor(doctor.getDoctorId(), newDoctor);

        verify(doctorRepository).save(doctor);
        verify(doctorRepository).findById(doctor.getDoctorId());
    }

    @Test
    void shouldThrowException_whenDoctorDoesntExist_whileUpdatingDoctor() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(10L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        Doctor newDoctor =
                Doctor.builder()
                        .doctorId(11L)
                        .emailAddress("markwhite71@gmail.com")
                        .build();

        given(doctorRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorService.updateDoctor(doctor.getDoctorId(), newDoctor));
    }

    @Test
    void whenGivenId_shouldDeleteDoctor_ifFound() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(20L)
                        .fullName("Mark White")
                        .emailAddress("markwhite74@gmail.com")
                        .build();

        when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));

        doctorService.deleteDoctor(doctor.getDoctorId());
        verify(doctorRepository).delete(doctor);
    }

    @Test
    void shouldThrowException_whenDoctorDoesntExist_whileDeletingDoctor() {
        Doctor doctor =
                Doctor.builder()
                        .doctorId(21L)
                        .fullName("Mark White")
                        .emailAddress("markwhite74@gmail.com")
                        .build();

        given(doctorRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorService.deleteDoctor(doctor.getDoctorId()));
    }
}