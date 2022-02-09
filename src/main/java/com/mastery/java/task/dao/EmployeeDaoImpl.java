package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addEmployee(Employee employee){
        jdbcTemplate.update("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)", employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getGender(), employee.getDateOfBirth());
    }

    @Override
    public void deleteEmployee(int id){
        jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", id);
    }

    @Override
    public void updateEmployee(int id, Employee updatedEmployee){
        jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=?, date_of_birth=? WHERE employee_id=?",
                updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getDepartmentId(), updatedEmployee.getJobTitle(),
                updatedEmployee.getGender(), updatedEmployee.getDateOfBirth(), id);
    }

    @Override
    public List<Employee> showEmployees(){
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee showOneEmployee(int id){
        return jdbcTemplate.query("SELECT * FROM employee WHERE employee_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }

}
