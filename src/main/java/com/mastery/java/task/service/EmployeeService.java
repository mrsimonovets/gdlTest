package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void deleteEmployee(Long id);

    void updateEmployee(Long id, Employee updatedEmployee);

    List<Employee> getAllEmployees();

    Employee getOneEmployee(Long id);

    Employee getOneEmployeeByFirstAndLastName(String firstName, String lastName);
}
