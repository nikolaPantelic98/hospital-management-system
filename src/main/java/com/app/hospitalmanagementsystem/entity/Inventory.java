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
        name = "inventory",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Long inventoryId;
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
    @ManyToMany(mappedBy = "inventories")
    @ToString.Exclude
    private List<PrescribedMedication> prescribedMedications;
}
