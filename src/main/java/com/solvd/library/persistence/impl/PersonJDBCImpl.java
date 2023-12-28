package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersonRepository;

import java.sql.Connection;
import java.util.List;

public class PersonJDBCImpl implements PersonRepository {

    @Override
    public Person findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Person> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Person person, Connection connection) {

    }

    @Override
    public void update(Person person, Connection connection) {

    }
}
