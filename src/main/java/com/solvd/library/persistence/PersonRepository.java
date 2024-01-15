package com.solvd.library.persistence;

import com.solvd.library.domain.Person;

import java.sql.Connection;
import java.util.List;

public interface PersonRepository extends GenericDAO<Person>{
    Person findById(Long id);
    List<Person> findAll();
    void create(Person person);
    void update(Person person);
}
