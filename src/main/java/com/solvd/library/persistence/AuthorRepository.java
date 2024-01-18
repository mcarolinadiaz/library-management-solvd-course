package com.solvd.library.persistence;

import com.solvd.library.domain.Author;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends GenericDAO<Author> {
    Optional<Author> findById(@Param("id") Long id);
    Collection<Author> findAll();
    void create(@Param("author") Author author);
    void update(@Param("author") Author author);
}
