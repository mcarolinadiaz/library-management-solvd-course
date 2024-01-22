package com.solvd.library.service.impl;


import com.solvd.library.domain.Employee;
import com.solvd.library.persistence.EmployeeRepository;
import com.solvd.library.persistence.impl.EmployeeJDBCImpl;
import com.solvd.library.persistence.impl.EmployeeMybatisImpl;
import com.solvd.library.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeJDBCImpl();
        //this.employeeRepository = new EmployeeMybatisImpl();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(null);
        employeeRepository.create(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeRepository.update(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.delete(id);
    }
}
