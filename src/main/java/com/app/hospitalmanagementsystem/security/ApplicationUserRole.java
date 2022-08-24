package com.app.hospitalmanagementsystem.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.app.hospitalmanagementsystem.security.ApplicationUserPermission.*;

@Getter
public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(APPOINTMENT_READ, APPOINTMENT_WRITE, BILL_READ, BILL_WRITE, DOCTOR_READ, DOCTOR_WRITE,
            DOCTOR_SERVICE_REPORT_SYSTEM_READ, DOCTOR_SERVICE_REPORT_SYSTEM_WRITE, DOCTORS_INFORMATION_READ,
            DOCTORS_INFORMATION_WRITE, DRUGS_INVENTORY_READ, DRUGS_INVENTORY_WRITE, INVENTORY_READ, INVENTORY_WRITE,
            LAB_TEST_READ, LAB_TEST_WRITE, MEDICAL_SERVICE_SYSTEM_READ, MEDICAL_SERVICE_SYSTEM_WRITE, PATIENT_READ,
            PATIENT_WRITE, PATIENT_MEDICAL_HISTORY_READ, PATIENT_MEDICAL_HISTORY_WRITE, PRESCRIBED_MEDICATION_READ,
            PRESCRIBED_MEDICATION_WRITE)),
    DOCTOR(Sets.newHashSet(APPOINTMENT_READ, DOCTOR_READ, DOCTORS_INFORMATION_READ, DRUGS_INVENTORY_READ,
            DRUGS_INVENTORY_WRITE, INVENTORY_READ, INVENTORY_WRITE, LAB_TEST_READ, LAB_TEST_WRITE,
            MEDICAL_SERVICE_SYSTEM_READ, PATIENT_READ, PATIENT_WRITE, PATIENT_MEDICAL_HISTORY_READ,
            PATIENT_MEDICAL_HISTORY_WRITE, PRESCRIBED_MEDICATION_READ, PRESCRIBED_MEDICATION_WRITE)),
    PATIENT(Sets.newHashSet(DOCTORS_INFORMATION_READ, MEDICAL_SERVICE_SYSTEM_READ)),
    RECEPTIONIST(Sets.newHashSet(APPOINTMENT_READ, APPOINTMENT_WRITE, BILL_READ, BILL_WRITE, DOCTOR_READ,
            DOCTORS_INFORMATION_READ, LAB_TEST_READ, MEDICAL_SERVICE_SYSTEM_READ, PATIENT_READ,
            PATIENT_WRITE, PATIENT_MEDICAL_HISTORY_READ, PATIENT_MEDICAL_HISTORY_WRITE, PRESCRIBED_MEDICATION_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
