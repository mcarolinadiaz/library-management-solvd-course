package com.solvd.library.service;

import com.solvd.library.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployeeById(Long id);
    Collection<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
