package com.solvd.library.persistence;

import com.solvd.library.domain.Publisher;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends GenericDAO<Publisher> {
    Optional<Publisher> findById(@Param("id") Long id);
    Collection<Publisher> findAll();
    void create(@Param("publisher") Publisher publisher);
    void update(@Param("publisher") Publisher publisher);
}
