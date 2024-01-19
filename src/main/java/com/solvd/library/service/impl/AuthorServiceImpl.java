package com.solvd.library.service.impl;


import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;
import com.solvd.library.persistence.impl.AuthorJDBCImpl;
import com.solvd.library.persistence.impl.AuthorMybatisImpl;
import com.solvd.library.service.AuthorService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorJDBCImpl();
        //this.authorRepository = new AuthorMybatisImpl();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Collection<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        author.setId(null);
        authorRepository.create(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        authorRepository.update(author);
        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.delete(id);
    }
}