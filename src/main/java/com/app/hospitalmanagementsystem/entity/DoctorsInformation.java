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
        name = "doctors_information",
        uniqueConstraints = @UniqueConstraint(
                name = "email_address_unique",
                columnNames = "email_address"
        )
)
public class DoctorsInformation {

    @Id
    @SequenceGenerator(
            name = "doctors_information_sequence",
            sequenceName = "doctors_information_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctors_information_sequence"
    )
    private Long doctorsInformationId;
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
            name = "email_address"
    )
    private String emailAddress;

    // TODO: experience, expertise and achievements
}
