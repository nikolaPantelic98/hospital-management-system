package com.app.hospitalmanagementsystem.serviceinterface;

import com.app.hospitalmanagementsystem.entity.Bill;

import java.util.List;

public interface BillServiceInterface {

    List<Bill> getAllBills();
    Bill getBillById(Long billId);
    Bill generateNewBill(Bill bill);
    Bill updateBill(Long billId, Bill billUpdatedDetails);
    void deleteBill(Long billId);
    Bill generateBillToPatient(Long billId, Long patientId);
}
