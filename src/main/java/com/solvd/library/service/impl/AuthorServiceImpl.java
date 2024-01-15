package com.solvd.library.service.impl;


import com.solvd.library.domain.Author;
import com.solvd.library.persistence.AuthorRepository;
import com.solvd.library.persistence.impl.AuthorJDBCImpl;
import com.solvd.library.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorJDBCImpl();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
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