package com.employeeManagement.demo.service;

import com.employeeManagement.demo.entity.Employee;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
