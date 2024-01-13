package com.solvd.library.persistence;

import com.solvd.library.domain.*;

import java.util.List;

public interface EmployeeRepository extends GenericDAO<Employee> {
    Employee findById(Long id);
    List<Employee> findAll();
    void create(Employee employee, Long branchId, Long personId);
    void update(Employee employee, Long branchId, Long personId);
    void delete(Long employee_id);

}
