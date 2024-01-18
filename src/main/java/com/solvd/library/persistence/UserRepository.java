package com.solvd.library.persistence;

import com.solvd.library.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericDAO<User> {
    Optional<User> findById(@Param("id") Long id);
    Collection<User> findAll();
    void create(@Param("user") User user);
    void update(@Param("user")User user);
    void delete(Long user_id);

}
