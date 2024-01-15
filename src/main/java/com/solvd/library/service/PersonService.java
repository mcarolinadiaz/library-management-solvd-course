package com.solvd.library.service;
import com.solvd.library.domain.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(Long id);
    List<Person> getAllPersons();
    Person createPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(Long id);
}
