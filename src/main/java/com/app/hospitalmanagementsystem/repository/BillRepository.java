package com.app.hospitalmanagementsystem.repository;

import com.app.hospitalmanagementsystem.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
