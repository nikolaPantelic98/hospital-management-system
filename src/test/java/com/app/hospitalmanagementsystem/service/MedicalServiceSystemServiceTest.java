package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.MedicalServiceSystem;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.MedicalServiceSystemRepository;
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
class MedicalServiceSystemServiceTest {

    @Mock
    private MedicalServiceSystemRepository medicalServiceSystemRepository;

    @InjectMocks
    private MedicalServiceSystemService medicalServiceSystemService;

    @Test
    void shouldGetAllMedicalServices() {
        List<MedicalServiceSystem> medicalServices = new ArrayList<>();
        medicalServices.add(new MedicalServiceSystem());

        given(medicalServiceSystemRepository.findAll()).willReturn(medicalServices);

        List<MedicalServiceSystem> expectedMedicalServices = medicalServiceSystemService.getAllMedicalServices();

        Assertions.assertEquals(expectedMedicalServices, medicalServices);
        verify(medicalServiceSystemRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnMedicalService_ifFound() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(1L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        when(medicalServiceSystemRepository.findById(medicalService.getMedicalServiceId())).thenReturn(Optional.of(medicalService));

        MedicalServiceSystem expectedMedicalService = medicalServiceSystemService.getMedicalServiceById(medicalService.getMedicalServiceId());

        assertThat(expectedMedicalService).isSameAs(medicalService);
        verify(medicalServiceSystemRepository).findById(medicalService.getMedicalServiceId());
    }

    @Test
    void shouldThrowException_whenMedicalServiceDoesntExist_whileReturningMedicalService() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(1L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        given(medicalServiceSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                medicalServiceSystemService.getMedicalServiceById(medicalService.getMedicalServiceId()));
    }

    @Test
    void whenSaveMedicalService_shouldReturnMedicalService() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .name("Blood")
                        .department("Hematology")
                        .build();

        when(medicalServiceSystemRepository.save(ArgumentMatchers.any(MedicalServiceSystem.class))).thenReturn(medicalService);

        MedicalServiceSystem createdMedicalService = medicalServiceSystemService.addNewMedicalService(medicalService);

        assertThat(createdMedicalService.getName()).isSameAs(medicalService.getName());
        assertThat(createdMedicalService.getDepartment()).isSameAs(medicalService.getDepartment());
        verify(medicalServiceSystemRepository).save(medicalService);
    }

    @Test
    void whenGivenId_shouldUpdateMedicalService_ifFound() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(10L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        MedicalServiceSystem newMedicalService =
                MedicalServiceSystem.builder()
                        .name("Fertility")
                        .department("Reproductive Endocrinology")
                        .build();

        given(medicalServiceSystemRepository.findById(medicalService.getMedicalServiceId())).willReturn(Optional.of(medicalService));
        medicalServiceSystemService.updateMedicalService(medicalService.getMedicalServiceId(), newMedicalService);

        verify(medicalServiceSystemRepository).save(medicalService);
        verify(medicalServiceSystemRepository).findById(medicalService.getMedicalServiceId());
    }

    @Test
    void shouldThrowException_whenMedicalServiceDoesntExist_whileUpdatingMedicalService() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(10L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        MedicalServiceSystem newMedicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(11L)
                        .name("Fertility")
                        .department("Reproductive Endocrinology")
                        .build();

        given(medicalServiceSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                medicalServiceSystemService.updateMedicalService(medicalService.getMedicalServiceId(), newMedicalService));
    }

    @Test
    void whenGivenId_shouldDeleteMedicalService_ifFound() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(20L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        when(medicalServiceSystemRepository.findById(medicalService.getMedicalServiceId())).thenReturn(Optional.of(medicalService));

        medicalServiceSystemService.deleteMedicalService(medicalService.getMedicalServiceId());
        verify(medicalServiceSystemRepository).delete(medicalService);
    }

    @Test
    void shouldThrowException_whenMedicalServiceDoesntExist_whileDeletingMedicalService() {
        MedicalServiceSystem medicalService =
                MedicalServiceSystem.builder()
                        .medicalServiceId(21L)
                        .name("Blood")
                        .department("Hematology")
                        .build();

        given(medicalServiceSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                medicalServiceSystemService.deleteMedicalService(medicalService.getMedicalServiceId()));
    }
}