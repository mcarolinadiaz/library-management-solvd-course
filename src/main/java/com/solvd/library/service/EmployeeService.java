package com.solvd.library.service;

import com.solvd.library.domain.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee, Long branchId, Long personId);
    Employee updateEmployee(Employee employee, Long branchId, Long personId);
    void deleteEmployee(Long id);
}
