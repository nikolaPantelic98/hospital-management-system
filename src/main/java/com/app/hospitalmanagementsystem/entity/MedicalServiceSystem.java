package com.app.hospitalmanagementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "medical_service",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class MedicalServiceSystem {

    @Id
    @SequenceGenerator(
            name = "medical_service_system_sequence",
            sequenceName = "medical_service_system_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medical_service_system_sequence"
    )
    private Long medicalServiceId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "department",
            nullable = false
    )
    private String department;
    @Column(
            name = "price"
    )
    private Integer price;
}
