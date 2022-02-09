package com.mastery.java.task.dto;


import java.util.Date;
import java.util.Objects;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, int departmentId, String jobTitle, String gender, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
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
