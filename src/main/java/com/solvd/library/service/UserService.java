package com.solvd.library.service;

import com.solvd.library.domain.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User createUser(User user, Long personId, Long reservationId);
    User updateUser(User user, Long personId, Long reservationId);
    void deleteUser(Long id);
}
