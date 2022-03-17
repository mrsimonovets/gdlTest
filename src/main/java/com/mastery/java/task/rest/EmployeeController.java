package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.NoSuchEmployeeException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.util.List;
import java.util.stream.Collectors;


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

    @Operation(summary = "Add employee",
            description = "Add employee to the database")
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody @Valid Employee employee){
//        if (bindingResult.hasErrors()){
//            logger.warn("Can not create the employee with not valid age");
//            return new ResponseEntity<>("Age should be more than 18", HttpStatus.BAD_REQUEST);
//        }
        employeeService.addEmployee(employee);
        logger.info("Employee " + employee.getFirstName() + " " + employee.getLastName() + " was added");
        return ResponseEntity.ok("Employee was successfully added");
    }

    @Operation(summary = "Delete employee",
            description = "Delete employee from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@DecimalMin(value = "1", message = "id should be more than 1")
                                                     @PathVariable Long id){
        logger.info("Employee with id = " + id + " was deleted");
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id = " + id + " was successfully deleted");
    }

    @Operation(summary = "Update employee",
            description = "Update employee in the database by id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@DecimalMin(value = "1", message = "id should be more than 1")
                                                     @PathVariable Long id , @RequestBody Employee employee){
        logger.info("Employee with id = " + id + " was updated");
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee with id = " + id + " was successfully updated");
    }

    @Operation(
            summary = "Get all employees",
            description = "You can get all employees and sort them by parameter from URL"
    )
    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "name", required = false)
                                              @Parameter(description = "Part of first or last name") String name){
        List<Employee> employees = employeeService.getAllEmployees();
        if(name != null){
            logger.info("Getting all employees that contain " + name + " in their name or surname");
            return employees.stream()
                    .filter((e) -> e.getFirstName().contains(name) || e.getLastName().contains(name))
                    .collect(Collectors.toList());
        }
        logger.info("Getting all employees");
        return employees;
    }

    @Operation(summary = "Get one employee",
            description = "Get employee from the database by id")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@DecimalMin(value = "1", message = "id should be more than 1")
                                        @PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null){
            logger.warn("No employee in Database with id = " + id);
            throw new NoSuchEmployeeException("There is no employee with id = " +
                    id + " in Database");
        }
        logger.info("Getting one employee with id = " + id);
        return employee;
    }
}
