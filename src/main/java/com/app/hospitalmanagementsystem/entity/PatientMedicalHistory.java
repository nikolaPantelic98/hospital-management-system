package com.app.hospitalmanagementsystem.entity;

import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

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
        name = "patient_medical_history"
)
public class PatientMedicalHistory {

    @Id
    @SequenceGenerator(
            name = "patient_medical_history_sequence",
            sequenceName = "patient_medical_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_medical_history_sequence"
    )
    private Long patientMedicalHistoryId;
    @Column(
            name = "date_of_treatment",
            nullable = false
    )
    private LocalDate dateOfTreatment;
    @Column(
            name = "treatment_description",
            nullable = false
    )
    private String treatmentDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "patientId"
    )
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "doctorId"
    )
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "bill_id",
            referencedColumnName = "billId"
    )
    private Bill bill;

    public void connectToPatient(Patient patient) {
        this.patient = patient;
    }

    public void connectToDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void addToBill(Bill bill) {
        this.bill = bill;
    }

    // TODO: Done!
}
