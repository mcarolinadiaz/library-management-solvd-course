package com.solvd.library.persistence;

import com.solvd.library.domain.Author;

import java.sql.Connection;
import java.util.List;

public interface AuthorRepository extends GenericDAO<Author> {
    Author findById(Long id, Connection connection);
    List<Author> findAll(Connection connection);
    void create(Author author, Connection connection);
    void update(Author author, Connection connection);
}
