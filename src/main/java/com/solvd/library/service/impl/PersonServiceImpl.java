package com.solvd.library.service.impl;


import com.solvd.library.domain.Person;
import com.solvd.library.persistence.PersonRepository;
import com.solvd.library.persistence.impl.PersonJDBCImpl;
import com.solvd.library.persistence.impl.PersonMybatisImpl;
import com.solvd.library.service.PersonService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl() {
       this.personRepository = new PersonJDBCImpl();
       //this.personRepository = new PersonMybatisImpl();
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Collection<Person> getAllPersons() {
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