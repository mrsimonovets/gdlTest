package com.mastery.java.task.dto;

import com.mastery.java.task.validator.ValidAge;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
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
    @Schema(example = "Developer")
    private String jobTitle;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Schema(example = "MALE")
    private Gender gender;

    @Column(name = "date_of_birth")
    @ValidAge
    @Schema(description = "Age should be more than 18", example = "2000-02-18")
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

//    @JsonCreator
    public Employee(Long id, String firstName, String lastName, int departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "Employee{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() && getDepartmentId() == employee.getDepartmentId() && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getJobTitle(), employee.getJobTitle()) && Objects.equals(getGender(), employee.getGender()) && Objects.equals(getDateOfBirth(), employee.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getDepartmentId(), getJobTitle(), getGender(), getDateOfBirth());
    }
}
