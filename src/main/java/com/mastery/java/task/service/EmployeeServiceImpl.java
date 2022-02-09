package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(int id, Employee updatedEmployee) {
        employeeDao.updateEmployee(id, updatedEmployee);
    }

    @Override
    public List<Employee> showEmployees() {
        return employeeDao.showEmployees();
    }

    @Override
    public Employee showOneEmployee(int id) {
        return employeeDao.showOneEmployee(id);
    }
}
