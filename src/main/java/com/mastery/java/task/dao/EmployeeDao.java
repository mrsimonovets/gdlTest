package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeDao {
    void addEmployee(Employee employee);

    void deleteEmployee(int id);

    void updateEmployee(int id, Employee updatedEmployee);

    List<Employee> getAllEmployees();

    Employee getOneEmployee(int id);
}
