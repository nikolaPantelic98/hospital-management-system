package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.DrugsInventory;

import java.util.List;

public interface DrugsInventoryServiceInterface {

    List<DrugsInventory> getAllDrugsInventories();
    DrugsInventory getDrugsInventoryById(Long drugId);
    DrugsInventory addNewDrugsInventory(DrugsInventory drug);
    DrugsInventory updateDrugsInventory(Long drugId, DrugsInventory drugUpdatedDetails);
    void deleteDrugsInventory(Long drugId);
}
