package com.solvd.library.persistence;

import com.solvd.library.domain.Person;

import java.sql.Connection;
import java.util.List;

public interface PersonRepository {
    Person findById(Long id, Connection connection);
    List<Person> findAll(Connection connection);
    void create(Person person, Connection connection);
    void update(Person person, Connection connection);
}
