package com.solvd.library.persistence;

import com.solvd.library.domain.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends GenericDAO<Person>{
    Optional<Person> findById(@Param("id") Long id);
    Collection<Person> findAll();
    void create(@Param("person") Person person);
    void update(@Param("person") Person person);
}
