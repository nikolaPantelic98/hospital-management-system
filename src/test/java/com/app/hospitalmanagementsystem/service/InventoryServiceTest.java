package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Inventory;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.InventoryRepository;
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
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void shouldGetAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory());

        given(inventoryRepository.findAll()).willReturn(inventories);

        List<Inventory> expectedInventories = inventoryService.getAllInventories();

        Assertions.assertEquals(expectedInventories, inventories);
        verify(inventoryRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnInventory_ifFound() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(1L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(500)
                        .build();

        when(inventoryRepository.findById(inventory.getInventoryId())).thenReturn(Optional.of(inventory));

        Inventory expectedInventory = inventoryService.getInventoryById(inventory.getInventoryId());

        assertThat(expectedInventory).isSameAs(inventory);
        verify(inventoryRepository).findById(inventory.getInventoryId());
    }

    @Test
    void shouldThrowException_whenInventoryDoesntExist_whilReturningInventory() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(1L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(500)
                        .build();

        given(inventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                inventoryService.getInventoryById(inventory.getInventoryId()));
    }

    @Test
    void whenSaveInventory_shouldReturnInventory() {
        Inventory inventory =
                Inventory.builder()
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(1000)
                        .build();

        when(inventoryRepository.save(ArgumentMatchers.any(Inventory.class))).thenReturn(inventory);

        Inventory createdInventory = inventoryService.addNewInventory(inventory);

        assertThat(createdInventory.getName()).isSameAs(inventory.getName());
        assertThat(createdInventory.getUnitOfMeasurement()).isSameAs(inventory.getUnitOfMeasurement());
        assertThat(createdInventory.getQuantityInStock()).isSameAs(inventory.getQuantityInStock());
        verify(inventoryRepository).save(inventory);
    }

    @Test
    void whenGivenId_shouldUpdateInventory_ifFound() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(10L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(500)
                        .build();

        Inventory newInventory =
                Inventory.builder()
                        .quantityInStock(400)
                        .build();

        given(inventoryRepository.findById(inventory.getInventoryId())).willReturn(Optional.of(inventory));
        inventoryService.updateInventory(inventory.getInventoryId(), newInventory);

        verify(inventoryRepository).save(inventory);
        verify(inventoryRepository).findById(inventory.getInventoryId());
    }

    @Test
    void shouldThrowException_whenInventoryDoesntExist_whileUpdatingInventory() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(10L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(500)
                        .build();

        Inventory newInventory =
                Inventory.builder()
                        .inventoryId(11L)
                        .quantityInStock(400)
                        .build();

        given(inventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                inventoryService.updateInventory(inventory.getInventoryId(), newInventory));
    }

    @Test
    void whenGivenId_shouldDeleteInventory_ifFound() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(20L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(250)
                        .build();

        when(inventoryRepository.findById(inventory.getInventoryId())).thenReturn(Optional.of(inventory));

        inventoryService.deleteInventory(inventory.getInventoryId());
        verify(inventoryRepository).delete(inventory);
    }

    @Test
    void shouldThrowException_whenInventoryDoesntExist_whileDeletingInventory() {
        Inventory inventory =
                Inventory.builder()
                        .inventoryId(21L)
                        .name("Bandage")
                        .unitOfMeasurement("Piece")
                        .quantityInStock(250)
                        .build();

        given(inventoryRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                inventoryService.deleteInventory(inventory.getInventoryId()));
    }
}