package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private JmsTemplate jmsTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JmsTemplate jmsTemplate) {
        this.employeeRepository = employeeRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public List<Employee> getAllEmployees(String firstName, String lastName) {
        return employeeRepository.findAllByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new MyServiceNotFoundException(String.format("There is no employee with id = %s", id)));
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void addEmployeeToQueue(Employee employee) {
        jmsTemplate.convertAndSend("firstqueue", employee);
    }

    @Override
    @JmsListener(destination = "firstqueue")
    public void readEmployeeFromQueue(Employee employee) {
        System.out.println(employee);
    }

    @Override
    public void updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new MyServiceNotFoundException(String.format("There is no employee with id = %s", id)));

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
        employeeRepository.findById(id).ifPresent(employee -> employeeRepository.deleteById(id));
    }

}
