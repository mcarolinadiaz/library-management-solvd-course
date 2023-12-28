package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.CommentRepository;
import com.solvd.library.persistence.CommentRepositoryRelationBook;
import com.solvd.library.persistence.CommentRepositoryRelationUser;

import java.sql.Connection;
import java.util.List;

public class CommentJDBCImpl implements CommentRepository, CommentRepositoryRelationBook, CommentRepositoryRelationUser {

    @Override
    public Object findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Object o, Connection connection) {

    }

    @Override
    public void update(Object o, Connection connection) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public void linkEntities(Comment t, Book r) {

    }

    @Override
    public void unlinkEntities(Comment t, Book r) {

    }

    @Override
    public void linkEntities(Comment t, User r) {

    }

    @Override
    public void unlinkEntities(Comment t, User r) {

    }
}
