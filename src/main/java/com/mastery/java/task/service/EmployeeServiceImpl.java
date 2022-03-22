package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import com.mastery.java.task.rest.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Employee> getAllEmployees(String firstName, String lastName) {
        return employeeRepository.findAllByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            logger.warn("No employee found with id = {}", id);
            return new MyServiceNotFoundException("There is no employee with id = " + id);
        });
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setDepartmentId(updatedEmployee.getDepartmentId());
        employee.setJobTitle(updatedEmployee.getJobTitle());
        employee.setGender(updatedEmployee.getGender());
        employee.setDateOfBirth(updatedEmployee.getDateOfBirth());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
