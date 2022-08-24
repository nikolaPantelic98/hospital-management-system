package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.Inventory;
import com.app.hospitalmanagementsystem.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('inventory:read')")
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("{inventoryId}")
    @PreAuthorize("hasAuthority('inventory:read')")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('inventory:write')")
    public Inventory addNewInventory(@RequestBody Inventory inventory) {
        return inventoryService.addNewInventory(inventory);
    }

    @PutMapping("{inventoryId}")
    @PreAuthorize("hasAuthority('inventory:write')")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long inventoryId, @RequestBody Inventory inventoryUpdatedDetails) {
        Inventory inventory = inventoryService.updateInventory(inventoryId, inventoryUpdatedDetails);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("{inventoryId}")
    @PreAuthorize("hasAuthority('inventory:write')")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
