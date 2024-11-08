package com.employeeManagement.demo.dao;

import com.employeeManagement.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // sort the employees by last name
    //Spring Data JPA will parse the method name, looks for specific format and pattern
    //JPA will creates appropriate query behind the scenes.
    //Service need to call this
    public List<Employee> findAllByOrderByLastNameAsc();

}
