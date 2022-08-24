package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.DrugsInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugsInventoryRepository extends JpaRepository<DrugsInventory, Long> {
}
