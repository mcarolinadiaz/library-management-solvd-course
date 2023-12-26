package com.solvd.library.persistence;

import com.solvd.library.domain.Person;

import java.util.List;

public interface PersonRepository {
    Person findById(int id);
    List<Person> findAll();
    void save(Person person);
    void update(Person person);
    void delete(int id);
}
