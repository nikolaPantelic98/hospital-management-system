package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.DrugsInventory;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.DrugsInventoryRepository;
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
class DrugsInventoryServiceTest {

    @Mock
    private DrugsInventoryRepository drugsInventoryRepository;

    @InjectMocks
    private DrugsInventoryService drugsInventoryService;

    @Test
    void shouldGetAllDrugInventories() {
        List<DrugsInventory> drugs = new ArrayList<>();
        drugs.add(new DrugsInventory());

        given(drugsInventoryRepository.findAll()).willReturn(drugs);

        List<DrugsInventory> expectedDrugs = drugsInventoryService.getAllDrugsInventories();

        Assertions.assertEquals(expectedDrugs, drugs);
        verify(drugsInventoryRepository).findAll();
    }

    @Test
    void whenGivenId_ShouldReturnDrugsInventory_IfFound() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(1L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(100)
                        .build();

        when(drugsInventoryRepository.findById(drug.getDrugId())).thenReturn(Optional.of(drug));

        DrugsInventory expectedDrug = drugsInventoryService.getDrugsInventoryById(drug.getDrugId());

        assertThat(expectedDrug).isSameAs(drug);
        verify(drugsInventoryRepository).findById(drug.getDrugId());
    }

    @Test
    void shouldThrowException_whenDrugsInventoryDoesntExist_whileReturningDrugsInventory() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(1L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(100)
                        .build();

        given(drugsInventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                drugsInventoryService.getDrugsInventoryById(drug.getDrugId()));
    }

    @Test
    void whenSaveDrugsInventory_shouldReturnDrugsInventory() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(50)
                        .build();

        when(drugsInventoryRepository.save(ArgumentMatchers.any(DrugsInventory.class))).thenReturn(drug);

        DrugsInventory createdDrug = drugsInventoryService.addNewDrugsInventory(drug);

        assertThat(createdDrug.getName()).isSameAs(drug.getName());
        assertThat(createdDrug.getUnitOfMeasurement()).isSameAs(drug.getUnitOfMeasurement());
        assertThat(createdDrug.getQuantityInStock()).isSameAs(drug.getQuantityInStock());
        verify(drugsInventoryRepository).save(drug);
    }

    @Test
    void whenGivenId_shouldUpdateDrugsInventory_ifFound() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(10L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(180)
                        .build();

        DrugsInventory newDrug =
                DrugsInventory.builder()
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(150)
                        .build();

        given(drugsInventoryRepository.findById(drug.getDrugId())).willReturn(Optional.of(drug));
        drugsInventoryService.updateDrugsInventory(drug.getDrugId(), newDrug);

        verify(drugsInventoryRepository).save(drug);
        verify(drugsInventoryRepository).findById(drug.getDrugId());
    }

    @Test
    void shouldThrowException_whenDrugsInventoryDoesntExist_whileUpdatingDrugsInventory() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(10L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(180)
                        .build();

        DrugsInventory newDrug =
                DrugsInventory.builder()
                        .drugId(11L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(150)
                        .build();

        given(drugsInventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                drugsInventoryService.updateDrugsInventory(drug.getDrugId(), newDrug));
    }

    @Test
    void whenGivenId_shouldDeleteDrugsInventory_ifFound() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(20L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(200)
                        .build();

        when(drugsInventoryRepository.findById(drug.getDrugId())).thenReturn(Optional.of(drug));

        drugsInventoryService.deleteDrugsInventory(drug.getDrugId());
        verify(drugsInventoryRepository).delete(drug);
    }

    @Test
    void shouldThrowException_whenDrugsInventoryDoesntExist_whileDeletingDrugsInventory() {
        DrugsInventory drug =
                DrugsInventory.builder()
                        .drugId(21L)
                        .name("Concerta")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(200)
                        .build();

        given(drugsInventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                drugsInventoryService.deleteDrugsInventory(drug.getDrugId()));
    }
}