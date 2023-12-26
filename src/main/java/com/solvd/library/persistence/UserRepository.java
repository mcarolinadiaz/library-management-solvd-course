package com.solvd.library.persistence;

import com.solvd.library.domain.User;

import java.util.List;

public interface UserRepository {
    User findById(int id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(int id);
}
