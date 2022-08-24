package com.app.hospitalmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "drugs_inventory",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class DrugsInventory {

    @Id
    @SequenceGenerator(
            name = "drugs_inventory_sequence",
            sequenceName = "drugs_inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drugs_inventory_sequence"
    )
    private Long drugId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "unit_of_measurement",
            nullable = false
    )
    private String unitOfMeasurement;
    @Column(
            name = "quantity_in_stock"
    )
    private Integer quantityInStock;

    @JsonIgnore
    @ManyToMany(mappedBy = "drugsInventories")
    @ToString.Exclude
    private List<PrescribedMedication> prescribedMedications;
}
