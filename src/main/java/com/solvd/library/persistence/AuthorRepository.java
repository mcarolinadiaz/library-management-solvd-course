package com.solvd.library.persistence;

import com.solvd.library.domain.Author;

import java.sql.Connection;
import java.util.List;

public interface AuthorRepository extends GenericDAO<Author> {
    Author findById(Long id);
    List<Author> findAll();
    void create(Author author);
    void update(Author author);
}
