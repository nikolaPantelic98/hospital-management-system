package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Bill;
import com.app.hospitalmanagementsystem.entity.Patient;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.BillRepository;
import com.app.hospitalmanagementsystem.repository.PatientRepository;
import com.app.hospitalmanagementsystem.serviceinterface.BillServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements BillServiceInterface {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public BillService(BillRepository billRepository, PatientRepository patientRepository) {
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " + billId + " doesn't exist."));
    }

    @Override
    public Bill generateNewBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Long billId, Bill billUpdatedDetails) {
        Bill updatedBill = billRepository.findById(billId)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " + billId + " doesn't exist."));
        updatedBill.setDateOfBill(billUpdatedDetails.getDateOfBill());
        updatedBill.setHospitalServicesDescription(billUpdatedDetails.getHospitalServicesDescription());
        updatedBill.setPrice(billUpdatedDetails.getPrice());
        return billRepository.save(updatedBill);
    }

    @Override
    public void deleteBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " + billId + " doesn't exist."));
        billRepository.delete(bill);
    }

    @Override
    public Bill generateBillToPatient(Long billId, Long patientId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " + billId + " doesn't exist."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id " + patientId + " doesn't exist."));
        bill.connectPatient(patient);
        return billRepository.save(bill);
    }
}
