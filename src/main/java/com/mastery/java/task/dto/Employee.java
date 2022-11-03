package com.mastery.java.task.dto;

import com.mastery.java.task.validator.ValidAge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "12")
    private Long id;

    @Column(name = "first_name")
    @Schema(example = "Ivan")
    private String firstName;

    @Column(name = "last_name")
    @Schema(example = "Ivanov")
    private String lastName;

    @Column(name = "department_id")
    @Schema(description = "Department where employee works", example = "1")
    private int departmentId;

    @Column(name = "job_title")
    @Schema(example = "DEVELOPER")
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Schema(example = "MALE")
    private Gender gender;

    @Column(name = "date_of_birth")
    @ValidAge
    @Schema(description = "Age should be more than 18", example = "2000-02-18")
    private LocalDate dateOfBirth;

//    public Employee(String firstName, String lastName, int departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.departmentId = departmentId;
//        this.jobTitle = jobTitle;
//        this.gender = gender;
//        this.dateOfBirth = dateOfBirth;
//    }

}
