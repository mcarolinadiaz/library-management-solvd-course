package com.solvd.library.persistence;

import com.solvd.library.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findById(int id);
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee);
    void delete(int id);
}
