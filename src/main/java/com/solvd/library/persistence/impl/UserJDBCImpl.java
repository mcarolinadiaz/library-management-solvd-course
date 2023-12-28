package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.Reservation;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.UserRepository;
import com.solvd.library.persistence.UserRepositoryContainsComment;
import com.solvd.library.persistence.UserRepositoryContainsLoans;

import java.sql.Connection;
import java.util.List;

public class UserJDBCImpl implements UserRepository, UserRepositoryContainsComment, UserRepositoryContainsLoans {

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public User findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<User> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(User user, Connection connection) {

    }

    @Override
    public void update(User user, Connection connection) {

    }

    @Override
    public void delete(Long user_id, Long person_id, Connection connection) {

    }

    @Override
    public void linkEntities(User e, Reservation r) {

    }

    @Override
    public void unlinkEntities(User e, Reservation r) {

    }

    @Override
    public void addEntity(User u, Comment c) {

    }

    @Override
    public void deleteEntity(User u, Comment c) {

    }

    @Override
    public void addEntity(User u, Loan l) {

    }

    @Override
    public void deleteEntity(User u, Loan l) {

    }
}
