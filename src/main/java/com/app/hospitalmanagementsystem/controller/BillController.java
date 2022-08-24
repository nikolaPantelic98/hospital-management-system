package com.app.hospitalmanagementsystem.controller;

import com.app.hospitalmanagementsystem.entity.Bill;
import com.app.hospitalmanagementsystem.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('bill:read')")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("{billId}")
    @PreAuthorize("hasAuthority('bill:read')")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        Bill bill = billService.getBillById(billId);
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('bill:write')")
    public Bill generateNewBill(Bill bill) {
        return billService.generateNewBill(bill);
    }

    @PutMapping("{billId}")
    @PreAuthorize("hasAuthority('bill:write')")
    public ResponseEntity<Bill> updateBill(@PathVariable Long billId, @RequestBody Bill billUpdatedDetails) {
        Bill bill = billService.updateBill(billId, billUpdatedDetails);
        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("{billId}")
    @PreAuthorize("hasAuthority('bill:write')")
    public ResponseEntity<Bill> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{billId}/patients/{patientId}")
    @PreAuthorize("hasAuthority('bill:write') and hasAuthority('patient:read')")
    public Bill generateBillToPatient(@PathVariable Long billId, @PathVariable Long patientId) {
        return billService.generateBillToPatient(billId, patientId);
    }
}
