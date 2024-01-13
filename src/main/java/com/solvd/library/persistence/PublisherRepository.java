package com.solvd.library.persistence;

import com.solvd.library.domain.Publisher;

import java.sql.Connection;
import java.util.List;

public interface PublisherRepository extends GenericDAO<Publisher> {
    Publisher findById(Long id);
    List<Publisher> findAll();
    void create(Publisher publisher);
    void update(Publisher publisher);
}
