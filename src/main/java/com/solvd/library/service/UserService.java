package com.solvd.library.service;

import com.solvd.library.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    Collection<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
