package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.DoctorsInformation;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.DoctorsInformationRepository;
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
class DoctorsInformationServiceTest {

    @Mock
    private DoctorsInformationRepository doctorsInformationRepository;

    @InjectMocks
    private DoctorsInformationService doctorsInformationService;

    @Test
    void shouldGetAllDoctorsInformation() {
        List<DoctorsInformation> doctorsInformations = new ArrayList<>();
        doctorsInformations.add(new DoctorsInformation());

        given(doctorsInformationRepository.findAll()).willReturn(doctorsInformations);

        List<DoctorsInformation> expectedDoctorsInformation = doctorsInformationService.getAllDoctorsInformation();

        Assertions.assertEquals(expectedDoctorsInformation, doctorsInformations);
        verify(doctorsInformationRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnDoctorsInformation_ifFound() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(1L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        when(doctorsInformationRepository.findById(doctorsInformation.getDoctorsInformationId())).thenReturn(Optional.of(doctorsInformation));

        DoctorsInformation expectedDoctorsInformation = doctorsInformationService.getDoctorsInformationById(doctorsInformation.getDoctorsInformationId());

        assertThat(expectedDoctorsInformation).isSameAs(doctorsInformation);
        verify(doctorsInformationRepository).findById(doctorsInformation.getDoctorsInformationId());
    }

    @Test
    void shouldThrowException_whenDoctorsInformationDoesntExist_whileReturningDoctorsInformation() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(1L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        given(doctorsInformationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorsInformationService.getDoctorsInformationById(doctorsInformation.getDoctorsInformationId()));
    }

    @Test
    void whenSaveDoctorsInformation_shouldReturnDoctorsInformation() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .fullName("Mark White")
                        .emailAddress("markwhite71@gmail.com")
                        .build();

        when(doctorsInformationRepository.save(ArgumentMatchers.any(DoctorsInformation.class))).thenReturn(doctorsInformation);

        DoctorsInformation createdDoctorsInformation = doctorsInformationService.addNewDoctorsInformation(doctorsInformation);

        assertThat(createdDoctorsInformation.getFullName()).isSameAs(doctorsInformation.getFullName());
        assertThat(createdDoctorsInformation.getEmailAddress()).isSameAs(doctorsInformation.getEmailAddress());
        verify(doctorsInformationRepository).save(doctorsInformation);
    }

    @Test
    void whenGivenId_shouldUpdateDoctorsInformation_ifFound() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(10L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        DoctorsInformation newDoctorsInformation =
                DoctorsInformation.builder()
                        .fullName("Mark Black")
                        .emailAddress("markblack@gmail.com")
                        .build();

        given(doctorsInformationRepository.findById(doctorsInformation.getDoctorsInformationId())).willReturn(Optional.of(doctorsInformation));
        doctorsInformationService.updateDoctorsInformation(doctorsInformation.getDoctorsInformationId(), newDoctorsInformation);

        verify(doctorsInformationRepository).save(doctorsInformation);
        verify(doctorsInformationRepository).findById(doctorsInformation.getDoctorsInformationId());
    }

    @Test
    void shouldThrowException_whenDoctorsInformationDoesntExist_whileUpdatingDoctorsInformation() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(10L)
                        .fullName("Mark White")
                        .emailAddress("markwhite@gmail.com")
                        .build();

        DoctorsInformation newDoctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(11L)
                        .fullName("Mark Black")
                        .emailAddress("markblack@gmail.com")
                        .build();

        given(doctorsInformationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorsInformationService.updateDoctorsInformation(doctorsInformation.getDoctorsInformationId(), newDoctorsInformation));
    }

    @Test
    void whenGivenId_shouldDeleteDoctorsInformation_ifFound() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(20L)
                        .fullName("Mark Black")
                        .emailAddress("markwhite@yahoo.com")
                        .build();

        when(doctorsInformationRepository.findById(doctorsInformation.getDoctorsInformationId())).thenReturn(Optional.of(doctorsInformation));

        doctorsInformationService.deleteDoctorsInformation(doctorsInformation.getDoctorsInformationId());
        verify(doctorsInformationRepository).delete(doctorsInformation);
    }

    @Test
    void shouldThrowException_whenDoctorsInformationDoesntExist_whileDeletingDoctorsInformation() {
        DoctorsInformation doctorsInformation =
                DoctorsInformation.builder()
                        .doctorsInformationId(21L)
                        .fullName("Mark Black")
                        .emailAddress("markwhite@yahoo.com")
                        .build();

        given(doctorsInformationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorsInformationService.deleteDoctorsInformation(doctorsInformation.getDoctorsInformationId()));
    }
}