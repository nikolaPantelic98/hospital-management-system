package com.app.hospitalmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "id_number_unique",
                        columnNames = "id_number"
                ),
                @UniqueConstraint(
                        name = "personal_identification_number_unique",
                        columnNames = "personal_identification_number"
                ),
                @UniqueConstraint(
                        name = "email_address_unique",
                        columnNames = "email_address"
                )
        }
)
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientId;
    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;
    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;
    @Column(
            name = "address",
            nullable = false
    )
    private String address;
    @Column(
            name = "id_number",
            nullable = false
    )
    private Long idNumber;
    @Column(
            name = "personal_identification_number",
            nullable = false
    )
    private Long personalIdentificationNumber;
    @Column(
            name = "email_address"
    )
    private String emailAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private List<Bill> bills;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private List<Appointment> appointments;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private List<LabTest> labTests;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private List<PatientMedicalHistory> patientMedicalHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private List<PrescribedMedication> prescribedMedications;

    // TODO: patientMedicalHistories (OneToMany), prescribedMedication (OneToMany), testResults,
    //  appointmentHistories (OneToMany), insuranceInformation,
    //  labTestSystem (pulse, blood pressure, CB glucose, O2 Saturation % etc.) (OneToMany)
}
