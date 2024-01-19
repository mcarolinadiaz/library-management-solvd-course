package com.solvd.library.service;

import com.solvd.library.domain.Author;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> getAuthorById(Long id);
    Collection<Author> getAllAuthors();
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}