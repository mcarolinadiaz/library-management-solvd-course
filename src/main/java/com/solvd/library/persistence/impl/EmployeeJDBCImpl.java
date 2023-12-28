package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Person;
import com.solvd.library.persistence.EmployeeRepoRelationalBranch;
import com.solvd.library.persistence.EmployeeRepoRelationalPerson;
import com.solvd.library.persistence.EmployeeRepository;

import java.sql.Connection;
import java.util.List;

public class EmployeeJDBCImpl implements EmployeeRepository, EmployeeRepoRelationalBranch, EmployeeRepoRelationalPerson {

    @Override
    public Employee findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Employee> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Employee entity, Connection connection) {

    }

    @Override
    public void create(Employee employee, Long person_id, Connection connection) {

    }

    @Override
    public void update(Employee employee, Connection connection) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public void delete(Long employee_id, Long person_id, Connection connection) {

    }

    @Override
    public void linkEntity(Employee e, Branch br) {

    }

    @Override
    public void unlinkEntity(Employee e, Branch br) {

    }

    @Override
    public void linkEntity(Employee e, Person p) {

    }

    @Override
    public void unlinkEntity(Employee e, Person p) {

    }
}
