package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/employees")
@Validated
@Tag(name = "Employee Controller")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Operation(
            summary = "Get all employees",
            description = "You can get all employees and sort them by parameter from URL"
    )
    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "firstName", defaultValue = "")
                                          @Parameter(description = "Part of first or last name") String firstName,
                                          @RequestParam(value = "lastName", defaultValue = "")
                                          @Parameter(description = "Part of first or last name") String lastName){
        logger.info("Getting all employees with parameters: firstName = {}, lastName = {}", firstName, lastName);
        List<Employee> employees = employeeService.getAllEmployees(firstName, lastName);
        return employees;
    }

    @Operation(summary = "Get one employee",
            description = "Get employee from the database by id")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@DecimalMin(value = "1", message = "id should be more than 1")
                                    @PathVariable Long id){
        logger.info("Getting one employee with id = {}", id);
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }

    @Operation(summary = "Add employee",
            description = "Add employee to the database")
    @PostMapping
    public void addEmployee(@RequestBody @Valid Employee employee){
        logger.info("Adding employee {}", employee);
        employeeService.addEmployee(employee);
    }

    @Operation(summary = "Update employee",
            description = "Update employee in the database by id")
    @PutMapping("/{id}")
    public void updateEmployee(@Positive(message = "id should be more than 1")
                               @PathVariable Long id , @RequestBody Employee updatedEmployee){
        logger.info("Updating employee with id = {}", id);
        logger.info("New parameters {}", updatedEmployee);
        employeeService.updateEmployee(id, updatedEmployee);
    }

    @Operation(summary = "Delete employee",
            description = "Delete employee from the database")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@Positive(message = "id should be more than 1") @PathVariable Long id){
        logger.info("Deleting employee with id = {}", id);
        employeeService.deleteEmployee(id);
    }
}
