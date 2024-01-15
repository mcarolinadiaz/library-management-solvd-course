package com.solvd.library.service;

import com.solvd.library.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}