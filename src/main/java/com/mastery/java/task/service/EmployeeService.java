package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee employee);

    public void deleteEmployee(int id);

    public void updateEmployee(int id, Employee updatedEmployee);

    public List<Employee> showEmployees();

    public Employee showOneEmployee(int id);
}
