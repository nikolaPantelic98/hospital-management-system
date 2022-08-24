package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
