package com.solvd.library.persistence;

import com.solvd.library.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends GenericDAO<Category> {
    Optional<Category> findById(@Param("id") Long id);
    Collection<Category> findAll();
    void create(@Param("category") Category category);
    void update(@Param("category") Category category);
}
