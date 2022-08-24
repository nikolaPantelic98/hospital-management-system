package com.app.hospitalmanagementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "lab_test"
)
public class LabTest {

    @Id
    @SequenceGenerator(
            name = "lab_test_sequence",
            sequenceName = "lab_test_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lab_test_sequence"
    )
    private Long labTestId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "test_details_description",
            nullable = false
    )
    private String testDetailsDescription;
    @Column(
            name = "date_when_lab_test_done",
            nullable = false
    )
    private LocalDate dateWhenLabTestDone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "patientId"
    )
    private Patient patient;

    public void connectToPatient(Patient patient) {
        this.patient = patient;
    }

    // TODO: Done!
}
