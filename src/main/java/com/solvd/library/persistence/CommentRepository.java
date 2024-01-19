package com.solvd.library.persistence;



import com.solvd.library.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends GenericDAO<Comment> {
    Optional<Comment> findById(@Param("id") Long id);
    Collection<Comment> findAll();
    void create(@Param("comment") Comment comment);

    void update(@Param("comment") Comment comment);
}
