package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.PrescribedMedication;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.PrescribedMedicationRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrescribedMedicationServiceTest {

    @Mock
    private PrescribedMedicationRepository prescribedMedicationRepository;

    @InjectMocks
    private PrescribedMedicationService prescribedMedicationService;

    @Test
    void shouldGetAllPrescribedMedications() {
        List<PrescribedMedication> prescribedMedications = new ArrayList<>();
        prescribedMedications.add(new PrescribedMedication());

        given(prescribedMedicationRepository.findAll()).willReturn(prescribedMedications);

        List<PrescribedMedication> expectedPrescribedMedications = prescribedMedicationService.getAllPrescribedMedications();

        Assertions.assertEquals(expectedPrescribedMedications, prescribedMedications);
        verify(prescribedMedicationRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnPrescribedMedication_ifFound() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(1L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2022, Month.APRIL, 11))
                        .build();

        when(prescribedMedicationRepository.findById(prescribedMedication.getPrescribedMedicationId())).thenReturn(Optional.of(prescribedMedication));

        PrescribedMedication expectedPrescribedMedication = prescribedMedicationService.getPrescribedMedicationById(prescribedMedication.getPrescribedMedicationId());

        assertThat(expectedPrescribedMedication).isSameAs(prescribedMedication);
        verify(prescribedMedicationRepository).findById(prescribedMedication.getPrescribedMedicationId());
    }

    @Test
    void shouldThrowException_whenPrescribedMedicationDoesntExist_whileReturningPrescribedMedication() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(1L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2022, Month.APRIL, 11))
                        .build();

        given(prescribedMedicationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                prescribedMedicationService.getPrescribedMedicationById(prescribedMedication.getPrescribedMedicationId()));
    }

    @Test
    void whenSavePrescribedMedication_shouldReturnPrescribedMedication() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2021, Month.MAY, 17))
                        .build();

        when(prescribedMedicationRepository.save(ArgumentMatchers.any(PrescribedMedication.class))).thenReturn(prescribedMedication);

        PrescribedMedication createdPrescribedMedication = prescribedMedicationService.addNewPrescribedMedication(prescribedMedication);

        assertThat(createdPrescribedMedication.getDateWhenMedicineWasPrescribed()).isSameAs(prescribedMedication.getDateWhenMedicineWasPrescribed());
        verify(prescribedMedicationRepository).save(prescribedMedication);
    }

    @Test
    void whenGivenId_shouldUpdatePrescribedMedication_ifFound() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(10L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2022, Month.APRIL, 11))
                        .build();

        PrescribedMedication newPrescribedMedication =
                PrescribedMedication.builder()
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2019, Month.JANUARY, 31))
                        .build();

        given(prescribedMedicationRepository.findById(prescribedMedication.getPrescribedMedicationId())).willReturn(Optional.of(prescribedMedication));
        prescribedMedicationService.updatePrescribedMedication(prescribedMedication.getPrescribedMedicationId(), newPrescribedMedication);

        verify(prescribedMedicationRepository).save(prescribedMedication);
        verify(prescribedMedicationRepository).findById(prescribedMedication.getPrescribedMedicationId());
    }

    @Test
    void shouldThrowException_whenPrescribedMedicationDoesntExist_whileUpdatingPrescribedMedication() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(10L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2022, Month.APRIL, 11))
                        .build();

        PrescribedMedication newPrescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(11L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2019, Month.JANUARY, 31))
                        .build();

        given(prescribedMedicationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                prescribedMedicationService.updatePrescribedMedication(prescribedMedication.getPrescribedMedicationId(), newPrescribedMedication));
    }

    @Test
    void whenGivenId_shouldDeletePrescribedMedication_ifFound() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(20L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2020, Month.APRIL, 11))
                        .build();

        when(prescribedMedicationRepository.findById(prescribedMedication.getPrescribedMedicationId())).thenReturn(Optional.of(prescribedMedication));

        prescribedMedicationService.deletePrescribedMedication(prescribedMedication.getPrescribedMedicationId());
        verify(prescribedMedicationRepository).delete(prescribedMedication);
    }

    @Test
    void shouldThrowException_whenPrescribedMedicationDoesntExist_whileDeletingPrescribedMedication() {
        PrescribedMedication prescribedMedication =
                PrescribedMedication.builder()
                        .prescribedMedicationId(21L)
                        .dateWhenMedicineWasPrescribed(LocalDate.of(2020, Month.APRIL, 11))
                        .build();

        given(prescribedMedicationRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                prescribedMedicationService.deletePrescribedMedication(prescribedMedication.getPrescribedMedicationId()));
    }
}