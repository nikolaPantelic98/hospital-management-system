package com.app.hospitalmanagementsystem.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationUserPermission {
    APPOINTMENT_READ("appointment:read"),
    APPOINTMENT_WRITE("appointment:write"),
    BILL_READ("bill:read"),
    BILL_WRITE("bill:write"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    DOCTOR_SERVICE_REPORT_SYSTEM_READ("doctor_service_report_system:read"),
    DOCTOR_SERVICE_REPORT_SYSTEM_WRITE("doctor_service_report_system:write"),
    DOCTORS_INFORMATION_READ("doctors_information:read"),
    DOCTORS_INFORMATION_WRITE("doctors_information:write"),
    DRUGS_INVENTORY_READ("drugs_inventory:read"),
    DRUGS_INVENTORY_WRITE("drugs_inventory:write"),
    INVENTORY_READ("inventory:read"),
    INVENTORY_WRITE("inventory:write"),
    LAB_TEST_READ("lab_test:read"),
    LAB_TEST_WRITE("lab_test:write"),
    MEDICAL_SERVICE_SYSTEM_READ("medical_service_system:read"),
    MEDICAL_SERVICE_SYSTEM_WRITE("medical_service_system:write"),
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write"),
    PATIENT_MEDICAL_HISTORY_READ("patient_medical_history:read"),
    PATIENT_MEDICAL_HISTORY_WRITE("patient_medical_history:write"),
    PRESCRIBED_MEDICATION_READ("prescribed_medication:read"),
    PRESCRIBED_MEDICATION_WRITE("prescribed_medication:write");

    private final String permission;
}
