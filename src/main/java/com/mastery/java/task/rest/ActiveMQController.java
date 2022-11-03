package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees/active")
@Tag(name = "ActiveMQ Controller")
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
public class ActiveMQController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    public ActiveMQController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Add employee",
            description = "Add employee through ActiveMQ to the database",
            responses = {
                    @ApiResponse(
                            description = "BAD REQUEST",
                            responseCode = "400",
                            content = @Content(schema = @Schema(type = "string"))
                    )
            })
    @PostMapping
    public void addEmployeeThroughQueue(@RequestBody @Valid Employee employee) {
        logger.info("IN: addEmployee() - employee: {}", employee);
        employeeService.addEmployeeToQueue(employee);
        employeeService.addEmployee(employee);
    }
}
