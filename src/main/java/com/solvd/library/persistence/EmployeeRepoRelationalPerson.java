package com.solvd.library.persistence;

import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Person;

public interface EmployeeRepoRelationalPerson {
    void linkEntity(Employee e, Person p);
    void unlinkEntity(Employee e, Person p);
}
