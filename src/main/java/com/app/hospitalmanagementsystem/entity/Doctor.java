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
        name = "doctor",
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
public class Doctor {

    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    private Long doctorId;
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
    @OneToOne(mappedBy = "doctor")
    private DoctorServiceReportSystem doctorServiceReportSystem;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<PatientMedicalHistory> patientMedicalHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<PrescribedMedication> prescribedMedications;
}
