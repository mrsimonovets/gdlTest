package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/employees")
@Validated
@Tag(name = "Employee Controller")
@ApiResponses(value = {
        @ApiResponse(
                description = "OK",
                responseCode = "200"
        ),
        @ApiResponse(
                description = "SERVER ERROR",
                responseCode = "500",
                content = @Content(schema = @Schema(type = "string"))
        )
})
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Get all employees",
            description = "You can get all employees and sort them by parameter from URL",
            responses = {
                    @ApiResponse(
                            description = "BAD REQUEST",
                            responseCode = "400",
                            content = @Content(schema = @Schema(type = "string"))
                    )
            })
    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "first_name", defaultValue = "")
                                          @Parameter(description = "Part of first name") String firstName,
                                          @RequestParam(value = "last_name", defaultValue = "")
                                          @Parameter(description = "Part of last name") String lastName) {
        logger.info("Method getAllEmployees() takes firstName = {}, lastName = {}", firstName, lastName);
        return employeeService.getAllEmployees(firstName, lastName);
    }

    @Operation(
            summary = "Get one employee",
            description = "Get employee from the database by id",
            responses = {
                    @ApiResponse(
                            description = "BAD REQUEST",
                            responseCode = "400",
                            content = @Content(schema = @Schema(type = "string"))
                    ),
                    @ApiResponse(
                            description = "NOT FOUND",
                            responseCode = "404",
                            content = @Content(schema = @Schema(type = "string"))
                    )
            })
    @GetMapping("/{id}")
    public Employee getEmployeeById(@Positive(message = "id should be more than 1")
                                    @PathVariable Long id) {
        logger.info("Method getEmployeeById() takes id = {}", id);
        return employeeService.getEmployeeById(id);
    }

    @Operation(
            summary = "Add employee",
            description = "Add employee to the database",
            responses = {
                    @ApiResponse(
                            description = "BAD REQUEST",
                            responseCode = "400",
                            content = @Content(schema = @Schema(type = "string"))
                    )
            })
    @PostMapping
    public void addEmployee(@RequestBody @Valid Employee employee) {
        logger.info("Method addEmployee() takes employee: {}", employee);
        employeeService.addEmployee(employee);
    }

    @Operation(
            summary = "Update employee",
            description = "Update employee by id",
            responses = {
                    @ApiResponse(
                            description = "BAD REQUEST",
                            responseCode = "400",
                            content = @Content(schema = @Schema(type = "string"))
                    ),
                    @ApiResponse(
                            description = "NOT FOUND",
                            responseCode = "404",
                            content = @Content(schema = @Schema(type = "string"))
                    )
            })
    @PutMapping("/{id}")
    public void updateEmployee(@Positive(message = "id should be more than 1")
                               @PathVariable Long id, @RequestBody @Valid Employee updatedEmployee) {
        logger.info("Method updateEmployee() takes id = {}, employee {}", id, updatedEmployee);
        employeeService.updateEmployee(id, updatedEmployee);
    }

    @Operation(
            summary = "Delete employee",
            description = "Delete employee from the database"
    )
    @DeleteMapping("/{id}")
    public void deleteEmployee(@Positive(message = "id should be more than 1") @PathVariable Long id) {
        logger.info("Method deleteEmployee() takes id = {}", id);
        employeeService.deleteEmployee(id);
    }
}
