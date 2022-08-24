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
        name = "doctor_service_report_system"
)
public class DoctorServiceReportSystem {

    @Id
    @SequenceGenerator(
            name = "doctor_service_report_system_sequence",
            sequenceName = "doctor_service_report_system_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_service_report_system_sequence"
    )
    private Long doctorServiceReportId;
    @Column(
            name = "specialization_field",
            nullable = false
    )
    private String specializationField;
    @Column(
            name = "work_efficiency",
            nullable = false
    )
    private String workEfficiency;
    @Column(
            name = "duty_hours",
            nullable = false
    )
    private Integer dutyHours;

    @OneToOne
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "doctorId"
    )
    private Doctor doctor;

    public void addToDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // TODO: Done!
}
