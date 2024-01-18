package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeRepository extends GenericDAO<Employee> {
    Optional<Employee> findById(@Param("id") Long id);
    Collection<Employee> findAll();
    void create(@Param("employee") Employee employee);
    void update(@Param("employee") Employee employee);
    void delete(@Param("id") Long employee_id);

}
