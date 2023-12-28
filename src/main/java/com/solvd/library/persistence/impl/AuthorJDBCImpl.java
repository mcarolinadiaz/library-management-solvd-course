package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;

import java.sql.Connection;
import java.util.List;

public class AuthorJDBCImpl implements AuthorRepository {


    @Override
    public Author findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Author> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Author author, Connection connection) {

    }

    @Override
    public void update(Author author, Connection connection) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }
}
