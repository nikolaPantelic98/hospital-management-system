package com.app.hospitalmanagementsystem.entity;

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
        name = "prescribed_medication"
)
public class PrescribedMedication {

    @Id
    @SequenceGenerator(
            name = "prescribed_medication_sequence",
            sequenceName = "prescribed_medication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "prescribed_medication_sequence"
    )
    private Long prescribedMedicationId;
    @Column(
            name = "date_when_medicine_was_prescribed",
            nullable = false
    )
    private LocalDate dateWhenMedicineWasPrescribed;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prescribed_medication_drugs_inventory_mapped",
            joinColumns = @JoinColumn(
                    name = "prescribed_medication_id",
                    referencedColumnName = "prescribedMedicationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "drug_id",
                    referencedColumnName = "drugId"
            )
    )
    @ToString.Exclude
    private List<DrugsInventory> drugsInventories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prescribed_medication_inventory_mapped",
            joinColumns = @JoinColumn(
                    name = "prescribed_medication_id",
                    referencedColumnName = "prescribedMedicationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "inventory_id",
                    referencedColumnName = "inventoryId"
            )
    )
    @ToString.Exclude
    private List<Inventory> inventories;

    public void addToPatient(Patient patient) {
        this.patient = patient;
    }

    public void addToDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void addToDrugsInventory(DrugsInventory drug) {
        drugsInventories.add(drug);
    }

    public void addToInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    // TODO: Done!
}
