package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id , @RequestBody Employee employee){
        employeeService.updateEmployee(id, employee);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> showEmployees(){
        List<Employee> employees = employeeService.showEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee showOneEmployee(@PathVariable int id){
        Employee employee = employeeService.showOneEmployee(id);
        return employee;
    }


}
