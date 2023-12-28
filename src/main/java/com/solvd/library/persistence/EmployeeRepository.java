package com.solvd.library.persistence;

import com.solvd.library.domain.*;

import java.sql.Connection;
import java.util.List;

public interface EmployeeRepository extends GenericDAO<Employee> {
    Employee findById(Long id, Connection connection);
    List<Employee> findAll(Connection connection);
    void create(Employee employee, Long person_id, Connection connection);
    void update(Employee employee, Connection connection);
    void delete(Long employee_id, Long person_id, Connection connection);

}
