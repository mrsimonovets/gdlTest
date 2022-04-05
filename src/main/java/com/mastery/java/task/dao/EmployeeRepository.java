package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}
