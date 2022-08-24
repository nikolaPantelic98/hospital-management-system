package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.DrugsInventory;
import com.app.hospitalmanagementsystem.service.DrugsInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/drugs-inventories")
public class DrugsInventoryController {

    private final DrugsInventoryService drugsInventoryService;

    @Autowired
    public DrugsInventoryController(DrugsInventoryService drugsInventoryService) {
        this.drugsInventoryService = drugsInventoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('drugs_inventory:read')")
    public List<DrugsInventory> getAllDrugsInventories() {
        return drugsInventoryService.getAllDrugsInventories();
    }

    @GetMapping("{drugId}")
    @PreAuthorize("hasAuthority('drugs_inventory:read')")
    public ResponseEntity<DrugsInventory> getDrugsInventoryById(@PathVariable Long drugId) {
        DrugsInventory drug = drugsInventoryService.getDrugsInventoryById(drugId);
        return ResponseEntity.ok(drug);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('drugs_inventory:write')")
    public DrugsInventory addNewDrugsInventory(@RequestBody DrugsInventory drug) {
        return drugsInventoryService.addNewDrugsInventory(drug);
    }

    @PutMapping("{drugId}")
    @PreAuthorize("hasAuthority('drugs_inventory:write')")
    public ResponseEntity<DrugsInventory> updateDrugsInventory(@PathVariable Long drugId, @RequestBody DrugsInventory drugUpdatedDetails) {
        DrugsInventory drug = drugsInventoryService.updateDrugsInventory(drugId, drugUpdatedDetails);
        return ResponseEntity.ok(drug);
    }

    @DeleteMapping("{drugId}")
    @PreAuthorize("hasAuthority('drugs_inventory:write')")
    public ResponseEntity<DrugsInventory> deleteDrugsInventory(@PathVariable Long drugId) {
        drugsInventoryService.deleteDrugsInventory(drugId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
