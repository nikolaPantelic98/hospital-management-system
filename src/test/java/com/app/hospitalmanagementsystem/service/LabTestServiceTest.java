package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.LabTest;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.LabTestRepository;
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
class LabTestServiceTest {

    @Mock
    private LabTestRepository labTestRepository;

    @InjectMocks
    private LabTestService labTestService;

    @Test
    void shouldGetAllLabTests() {
        List<LabTest> labTests = new ArrayList<>();
        labTests.add(new LabTest());

        given(labTestRepository.findAll()).willReturn(labTests);

        List<LabTest> expectedLabTests = labTestService.getAllLabTests();

        Assertions.assertEquals(expectedLabTests, labTests);
        verify(labTestRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnLabTest_ifFound() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(1L)
                        .name("Hemoglobin A1C")
                        .build();

        when(labTestRepository.findById(labTest.getLabTestId())).thenReturn(Optional.of(labTest));

        LabTest expectedLabTest = labTestService.getLabTestById(labTest.getLabTestId());

        assertThat(expectedLabTest).isSameAs(labTest);
        verify(labTestRepository).findById(labTest.getLabTestId());
    }

    @Test
    void shouldThrowException_whenLabTestDoesntExist_whileReturningLabTest() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(1L)
                        .name("Hemoglobin A1C")
                        .build();

        given(labTestRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labTestService.getLabTestById(labTest.getLabTestId()));
    }

    @Test
    void whenSaveLabTest_shouldReturnLabTest() {
        LabTest labTest =
                LabTest.builder()
                        .name("Complete Blood Count")
                        .build();

        when(labTestRepository.save(ArgumentMatchers.any(LabTest.class))).thenReturn(labTest);

        LabTest createdLabTest = labTestService.addNewLabTest(labTest);

        assertThat(createdLabTest.getName()).isSameAs(labTest.getName());
        verify(labTestRepository).save(labTest);
    }

    @Test
    void whenGivenId_shouldUpdateLabTest_ifFound() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(10L)
                        .name("Hemoglobin A1C")
                        .build();

        LabTest newLabTest =
                LabTest.builder()
                        .name("Lipid Panel")
                        .build();

        given(labTestRepository.findById(labTest.getLabTestId())).willReturn(Optional.of(labTest));
        labTestService.updateLabTest(labTest.getLabTestId(), newLabTest);

        verify(labTestRepository).save(labTest);
        verify(labTestRepository).findById(labTest.getLabTestId());
    }

    @Test
    void shouldThrowException_whenLabTestDoesntExist_whileUpdatingLabTest() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(10L)
                        .name("Hemoglobin A1C")
                        .build();

        LabTest newLabTest =
                LabTest.builder()
                        .labTestId(11L)
                        .name("Lipid Panel")
                        .build();

        given(labTestRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labTestService.updateLabTest(labTest.getLabTestId(), newLabTest));
    }

    @Test
    void whenGivenId_shouldDeleteLabTest_ifFound() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(20L)
                        .name("Hemoglobin A1C")
                        .build();

        when(labTestRepository.findById(labTest.getLabTestId())).thenReturn(Optional.of(labTest));

        labTestService.deleteLabTest(labTest.getLabTestId());
        verify(labTestRepository).delete(labTest);
    }

    @Test
    void shouldThrowException_whenLabTestDoesntExist_whileDeletingLabTest() {
        LabTest labTest =
                LabTest.builder()
                        .labTestId(21L)
                        .name("Hemoglobin A1C")
                        .build();

        given(labTestRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labTestService.deleteLabTest(labTest.getLabTestId()));
    }
}