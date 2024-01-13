package com.solvd.library.service.impl;


import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersonRepository;
import com.solvd.library.persistence.impl.PersonJDBCImpl;
import com.solvd.library.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl() {
        this.personRepository = new PersonJDBCImpl();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person createPerson(Person person) {
        person.setId(null);
        personRepository.create(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        personRepository.update(person);
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(id);
    }
}