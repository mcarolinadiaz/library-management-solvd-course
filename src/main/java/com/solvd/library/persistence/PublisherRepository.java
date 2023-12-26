package com.solvd.library.persistence;

import com.solvd.library.domain.Publisher;

import java.util.List;

public interface PublisherRepository {
    Publisher findById(int id);
    List<Publisher> findAll();
    void save(Publisher publisher);
    void update(Publisher publisher);
    void delete(int id);
}
