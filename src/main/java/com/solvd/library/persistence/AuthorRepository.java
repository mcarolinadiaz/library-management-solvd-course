package com.solvd.library.persistence;

import com.solvd.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends GenericDAO<Author> {
    Author findById(int id);
    List<Author> findAll();
    void create(Author author);
    void update(Author author);
    void delete(int id);
}
