package com.solvd.library.service;
import com.solvd.library.domain.Person;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> getPersonById(Long id);
    Collection<Person> getAllPersons();
    Person createPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(Long id);
}
