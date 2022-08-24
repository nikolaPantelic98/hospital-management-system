package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.Inventory;

import java.util.List;

public interface InventoryServiceInterface {

    List<Inventory> getAllInventories();
    Inventory getInventoryById(Long inventoryId);
    Inventory addNewInventory(Inventory inventory);
    Inventory updateInventory(Long inventoryId, Inventory inventoryUpdatedDetails);
    void deleteInventory(Long inventoryId);
}
