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
        name = "bill"
)
public class Bill {

    @Id
    @SequenceGenerator(
            name = "bill_sequence",
            sequenceName = "bill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_sequence"
    )
    private Long billId;
    @Column(
            name = "date_of_bill",
            nullable = false
    )
    private LocalDate dateOfBill;
    @Column(
            name = "hospital_services_description",
            nullable = false
    )
    private String hospitalServicesDescription;
    @Column(
            name = "price",
            nullable = false
    )
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "patientId"
    )
    private Patient patient;

    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    @ToString.Exclude
    private List<PatientMedicalHistory> patientMedicalHistories;

    public void connectPatient(Patient patient) {
        this.patient = patient;
    }

    // TODO: Done!
}
