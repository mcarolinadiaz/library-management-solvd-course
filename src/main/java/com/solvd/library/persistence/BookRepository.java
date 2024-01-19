package com.solvd.library.persistence;

import com.solvd.library.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends GenericDAO<Book> {
    Optional<Book> findById(@Param("id") Long id);
    Collection<Book> findAll();
    void create(@Param("book") Book book);
    void update(@Param("book") Book book);
    void delete(@Param("id") Long id);
}
