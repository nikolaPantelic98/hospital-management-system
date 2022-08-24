package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.DoctorServiceReportSystem;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.DoctorServiceReportSystemRepository;
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
class DoctorServiceReportSystemServiceTest {

    @Mock
    private DoctorServiceReportSystemRepository doctorServiceReportSystemRepository;

    @InjectMocks
    private DoctorServiceReportSystemService doctorServiceReportSystemService;

    @Test
    void shouldGetAllDoctorServiceReports() {
        List<DoctorServiceReportSystem> doctorServiceReportSystems = new ArrayList<>();
        doctorServiceReportSystems.add(new DoctorServiceReportSystem());

        given(doctorServiceReportSystemRepository.findAll()).willReturn(doctorServiceReportSystems);

        List<DoctorServiceReportSystem> expectedDoctorServiceReportSystem = doctorServiceReportSystemService.getAllDoctorServiceReports();

        Assertions.assertEquals(expectedDoctorServiceReportSystem, doctorServiceReportSystems);
        verify(doctorServiceReportSystemRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnDoctorServiceReport_ifFound() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(1L)
                        .specializationField("Cardiology")
                        .dutyHours(220)
                        .build();

        when(doctorServiceReportSystemRepository.findById(doctorServiceReport.getDoctorServiceReportId())).thenReturn(Optional.of(doctorServiceReport));

        DoctorServiceReportSystem expectedDoctorServiceReport = doctorServiceReportSystemService.getDoctorServiceReportById(doctorServiceReport.getDoctorServiceReportId());

        assertThat(expectedDoctorServiceReport).isSameAs(doctorServiceReport);
        verify(doctorServiceReportSystemRepository).findById(doctorServiceReport.getDoctorServiceReportId());
    }

    @Test
    void shouldThrowException_whenDoctorServiceReportDoesntExist_whileReturningDoctorServiceReport() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(1L)
                        .specializationField("Cardiology")
                        .dutyHours(220)
                        .build();

        given(doctorServiceReportSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorServiceReportSystemService.getDoctorServiceReportById(doctorServiceReport.getDoctorServiceReportId()));
    }

    @Test
    void whenSaveDoctorReport_shouldReturnDoctorReport() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .specializationField("Cardiology")
                        .dutyHours(180)
                        .build();

        when(doctorServiceReportSystemRepository.save(ArgumentMatchers.any(DoctorServiceReportSystem.class))).thenReturn(doctorServiceReport);

        DoctorServiceReportSystem createdDoctorServiceReport = doctorServiceReportSystemService.addNewDoctorServiceReport(doctorServiceReport);

        assertThat(createdDoctorServiceReport.getSpecializationField()).isSameAs(doctorServiceReport.getSpecializationField());
        assertThat(createdDoctorServiceReport.getDutyHours()).isSameAs(doctorServiceReport.getDutyHours());
        verify(doctorServiceReportSystemRepository).save(doctorServiceReport);
    }

    @Test
    void whenGivenId_shouldUpdateDoctorServiceReport_ifFound() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(10L)
                        .specializationField("Cardiology")
                        .dutyHours(220)
                        .build();

        DoctorServiceReportSystem newDoctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .specializationField("Dermatology")
                        .dutyHours(180)
                        .build();

        given(doctorServiceReportSystemRepository.findById(doctorServiceReport.getDoctorServiceReportId())).willReturn(Optional.of(doctorServiceReport));
        doctorServiceReportSystemService.updateDoctorServiceReport(doctorServiceReport.getDoctorServiceReportId(), newDoctorServiceReport);

        verify(doctorServiceReportSystemRepository).save(doctorServiceReport);
        verify(doctorServiceReportSystemRepository).findById(doctorServiceReport.getDoctorServiceReportId());
    }

    @Test
    void shouldThrowException_whenDoctorServiceReportDoesntExist_whileUpdatingDoctorServiceReport() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(10L)
                        .specializationField("Cardiology")
                        .dutyHours(220)
                        .build();

        DoctorServiceReportSystem newDoctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(11L)
                        .specializationField("Dermatology")
                        .dutyHours(180)
                        .build();

        given(doctorServiceReportSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorServiceReportSystemService.updateDoctorServiceReport(doctorServiceReport.getDoctorServiceReportId(), newDoctorServiceReport));
    }

    @Test
    void whenGivenId_shouldDeleteDoctorServiceReport_ifFounded() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(20L)
                        .specializationField("Cardiology")
                        .dutyHours(195)
                        .build();

        when(doctorServiceReportSystemRepository.findById(doctorServiceReport.getDoctorServiceReportId())).thenReturn(Optional.of(doctorServiceReport));

        doctorServiceReportSystemService.deleteDoctorServiceReport(doctorServiceReport.getDoctorServiceReportId());
        verify(doctorServiceReportSystemRepository).delete(doctorServiceReport);
    }

    @Test
    void shouldThrowException_whenDoctorServiceReportDoesntExist_whileDeletingDoctorServiceReport() {
        DoctorServiceReportSystem doctorServiceReport =
                DoctorServiceReportSystem.builder()
                        .doctorServiceReportId(21L)
                        .specializationField("Cardiology")
                        .dutyHours(195)
                        .build();

        given(doctorServiceReportSystemRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                doctorServiceReportSystemService.deleteDoctorServiceReport(doctorServiceReport.getDoctorServiceReportId()));
    }
}