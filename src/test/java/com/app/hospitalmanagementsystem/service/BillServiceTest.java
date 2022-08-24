package com.app.hospitalmanagementsystem.service;

import com.app.hospitalmanagementsystem.entity.Bill;
import com.app.hospitalmanagementsystem.exception.ResourceNotFoundException;
import com.app.hospitalmanagementsystem.repository.BillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    @Test
    void shouldGetAllBills() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill());

        given(billRepository.findAll()).willReturn(bills);

        List<Bill> expectedBills = billService.getAllBills();

        Assertions.assertEquals(expectedBills, bills);
        verify(billRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnBill_ifFounded() {
        Bill bill =
                Bill.builder()
                        .billId(1L)
                        .hospitalServicesDescription("Cardiology")
                        .price(200)
                        .build();

        when(billRepository.findById(bill.getBillId())).thenReturn(Optional.of(bill));

        Bill expectedBill = billService.getBillById(bill.getBillId());

        assertThat(expectedBill).isSameAs(bill);
        verify(billRepository).findById(bill.getBillId());
    }

    @Test
    void shouldThrowException_whenBillDoesntExist_whileReturningBill() {
        Bill bill =
                Bill.builder()
                        .billId(1L)
                        .hospitalServicesDescription("Cardiology")
                        .price(200)
                        .build();

        given(billRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                billService.getBillById(bill.getBillId()));
    }

    @Test
    void whenSaveBill_shouldReturnBill() {
        Bill bill =
                Bill.builder()
                        .hospitalServicesDescription("Cardiology")
                        .price(100)
                        .build();

        when(billRepository.save(ArgumentMatchers.any(Bill.class))).thenReturn(bill);

        Bill createdBill = billService.generateNewBill(bill);

        assertThat(createdBill.getHospitalServicesDescription()).isSameAs(bill.getHospitalServicesDescription());
        assertThat(createdBill.getPrice()).isSameAs(bill.getPrice());
        verify(billRepository).save(bill);
    }

    @Test
    void whenGivenId_shouldUpdateBill_ifFound() {
        Bill bill =
                Bill.builder()
                        .billId(10L)
                        .hospitalServicesDescription("Cardiology")
                        .price(100)
                        .build();

        Bill newBill =
                Bill.builder()
                        .price(400)
                        .build();

        given(billRepository.findById(bill.getBillId())).willReturn(Optional.of(bill));
        billService.updateBill(bill.getBillId(), newBill);

        verify(billRepository).save(bill);
        verify(billRepository).findById(bill.getBillId());
    }

    @Test
    void shouldThrowException_whenBillDoesntExist_whileUpdatingBill() {
        Bill bill =
                Bill.builder()
                        .billId(10L)
                        .hospitalServicesDescription("Cardiology")
                        .price(100)
                        .build();

        Bill newBill =
                Bill.builder()
                        .billId(11L)
                        .price(400)
                        .build();

        given(billRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                billService.updateBill(bill.getBillId(), newBill));
    }

    @Test
    void whenGivenId_shouldDeleteBill_ifFounded() {
        Bill bill =
                Bill.builder()
                        .billId(20L)
                        .hospitalServicesDescription("Cardiology")
                        .price(120)
                        .build();

        when(billRepository.findById(bill.getBillId())).thenReturn(Optional.of(bill));

        billService.deleteBill(bill.getBillId());
        verify(billRepository).delete(bill);
    }

    @Test
    void shouldThrowException_whenBillDoesntExist_whileDeletingBill() {
        Bill bill =
                Bill.builder()
                        .billId(21L)
                        .hospitalServicesDescription("Cardiology")
                        .price(120)
                        .build();

        given(billRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                billService.deleteBill(bill.getBillId()));
    }
}