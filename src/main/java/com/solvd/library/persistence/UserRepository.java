package com.solvd.library.persistence;

import com.solvd.library.domain.*;
import com.solvd.library.domain.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends GenericDAO<User>, RelationalDAO<User, Reservation> {
    User findById(Long id, Connection connection);
    List<User> findAll(Connection connection);
    void create(User user, Connection connection);
    void update(User user, Connection connection);
    void delete(Long user_id, Long person_id, Connection connection);
    void linkEntities(User e, Reservation r);
    void unlinkEntities(User e, Reservation r);
}
