package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import com.solvd.library.domain.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends GenericDAO<User> {
    User findById(Long id);
    List<User> findAll();
    void create(User user, Long personId, Long reservationId);
    void update(User user, Long personId, Long reservationId);
    void delete(Long user_id);

}
