package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.PatientMedicalHistory;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.PatientMedicalHistoryRepository;
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
class PatientMedicalHistoryServiceTest {

    @Mock
    private PatientMedicalHistoryRepository patientMedicalHistoryRepository;

    @InjectMocks
    private PatientMedicalHistoryService patientMedicalHistoryService;

    @Test
    void shouldGetAllPatientMedicalHistories() {
        List<PatientMedicalHistory> patientMedicalHistories = new ArrayList<>();
        patientMedicalHistories.add(new PatientMedicalHistory());

        given(patientMedicalHistoryRepository.findAll()).willReturn(patientMedicalHistories);

        List<PatientMedicalHistory> expectedPatientMedicalHistories = patientMedicalHistoryService.getAllPatientMedicalHistories();

        Assertions.assertEquals(expectedPatientMedicalHistories, patientMedicalHistories);
        verify(patientMedicalHistoryRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnPatientMedicalHistory_ifFound() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(1L)
                        .dateOfTreatment(LocalDate.of(2021, Month.AUGUST, 19))
                        .build();

        when(patientMedicalHistoryRepository.findById(patientMedicalHistory.getPatientMedicalHistoryId())).thenReturn(Optional.of(patientMedicalHistory));

        PatientMedicalHistory expectedPatientMedicalHistory = patientMedicalHistoryService.getPatientMedicalHistoryById(patientMedicalHistory.getPatientMedicalHistoryId());

        assertThat(expectedPatientMedicalHistory).isSameAs(patientMedicalHistory);
        verify(patientMedicalHistoryRepository).findById(patientMedicalHistory.getPatientMedicalHistoryId());
    }

    @Test
    void shouldThrowException_whenPatientMedicalHistoryDoesntExist_whileReturningPatientMedicalHistory() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(1L)
                        .dateOfTreatment(LocalDate.of(2021, Month.AUGUST, 19))
                        .build();

        given(patientMedicalHistoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientMedicalHistoryService.getPatientMedicalHistoryById(patientMedicalHistory.getPatientMedicalHistoryId()));
    }

    @Test
    void whenSavePatientMedicalHistory_shouldReturnPatientMedicalHistory() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .dateOfTreatment(LocalDate.of(2020, Month.AUGUST, 19))
                        .build();

        when(patientMedicalHistoryRepository.save(ArgumentMatchers.any(PatientMedicalHistory.class))).thenReturn(patientMedicalHistory);

        PatientMedicalHistory createdPatientMedicalHistory = patientMedicalHistoryService.addNewPatientMedicalHistory(patientMedicalHistory);

        assertThat(createdPatientMedicalHistory.getDateOfTreatment()).isSameAs(patientMedicalHistory.getDateOfTreatment());
        verify(patientMedicalHistoryRepository).save(patientMedicalHistory);
    }

    @Test
    void whenGivenId_shouldUpdatePatientMedicalHistory_ifFound() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(10L)
                        .dateOfTreatment(LocalDate.of(2021, Month.AUGUST, 19))
                        .build();

        PatientMedicalHistory newPatientMedicalHistory =
                PatientMedicalHistory.builder()
                        .dateOfTreatment(LocalDate.of(2020, Month.MAY, 11))
                        .build();

        given(patientMedicalHistoryRepository.findById(patientMedicalHistory.getPatientMedicalHistoryId())).willReturn(Optional.of(patientMedicalHistory));
        patientMedicalHistoryService.updatePatientMedicalHistory(patientMedicalHistory.getPatientMedicalHistoryId(), newPatientMedicalHistory);

        verify(patientMedicalHistoryRepository).save(patientMedicalHistory);
        verify(patientMedicalHistoryRepository).findById(patientMedicalHistory.getPatientMedicalHistoryId());
    }

    @Test
    void shouldThrowException_whenPatientMedicalHistoryDoesntExist_whileUpdatingPatientMedicalHistory() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(10L)
                        .dateOfTreatment(LocalDate.of(2021, Month.AUGUST, 19))
                        .build();

        PatientMedicalHistory newPatientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(11L)
                        .dateOfTreatment(LocalDate.of(2020, Month.MAY, 11))
                        .build();

        given(patientMedicalHistoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientMedicalHistoryService.updatePatientMedicalHistory(patientMedicalHistory.getPatientMedicalHistoryId(), newPatientMedicalHistory));
    }

    @Test
    void whenGivenId_shouldDeletePatientMedicalHistory_ifFound() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(20L)
                        .dateOfTreatment(LocalDate.of(2020, Month.SEPTEMBER, 10))
                        .build();

        when(patientMedicalHistoryRepository.findById(patientMedicalHistory.getPatientMedicalHistoryId())).thenReturn(Optional.of(patientMedicalHistory));

        patientMedicalHistoryService.deletePatientMedicalHistory(patientMedicalHistory.getPatientMedicalHistoryId());
        verify(patientMedicalHistoryRepository).delete(patientMedicalHistory);
    }

    @Test
    void shouldThrowException_whenPatientMedicalHistoryDoesntExist_whileDeletingPatientMedicalHistory() {
        PatientMedicalHistory patientMedicalHistory =
                PatientMedicalHistory.builder()
                        .patientMedicalHistoryId(21L)
                        .dateOfTreatment(LocalDate.of(2020, Month.SEPTEMBER, 10))
                        .build();

        given(patientMedicalHistoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                patientMedicalHistoryService.deletePatientMedicalHistory(patientMedicalHistory.getPatientMedicalHistoryId()));
    }
}