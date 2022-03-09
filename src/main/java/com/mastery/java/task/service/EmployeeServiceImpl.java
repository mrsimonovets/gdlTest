package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

//    private final EmployeeDao employeeDao;
//
//    public EmployeeServiceImpl(EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
//        employeeDao.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
//        employeeDao.deleteEmployee(id);
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
//        employeeDao.updateEmployee(id, updatedEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
//        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getOneEmployee(Long id) {
        Employee employee = null;
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        return employee;
//        return employeeDao.getOneEmployee(id);
    }

    @Override
    public Employee getOneEmployeeByFirstAndLastName(String firstName, String lastName){

        return employeeRepository.getEmployeeByFirstNameAndLastName(firstName, lastName);
    }
}
