package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception_handling.CustomExceptionHandler;
import com.mastery.java.task.exception_handling.NoSuchEmployeeException;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
@CustomExceptionHandler
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    public void addEmployee(@Validated @RequestBody Employee employee){
        logger.info("Add employee");
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id , @RequestBody Employee employee){
        employeeService.updateEmployee(id, employee);
        return employee;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@Validated @PathVariable Long id){
        Employee employee = employeeService.getOneEmployee(id);

        if (employee == null){
            logger.warn("No employee in Database");
            throw new NoSuchEmployeeException("There is no employee with id = " +
                    id + " in Database");
        }

        return employee;
    }

    @GetMapping("/name/{firstName}/{lastName}")
    public Employee getEmployeeByFirstAndLastName(@PathVariable String firstName,
                                                        @PathVariable String lastName){
        return employeeService.getOneEmployeeByFirstAndLastName(firstName, lastName);

    }

}
