package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Loan;
import com.solvd.library.domain.User;
import com.solvd.library.persistence.LoanRepository;
import com.solvd.library.persistence.LoanRepositoryRelationBook;
import com.solvd.library.persistence.LoanRepositoryRelationUser;

import java.sql.Connection;
import java.util.List;

public class LoanJDBCImpl implements LoanRepository, LoanRepositoryRelationBook, LoanRepositoryRelationUser {

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public Loan findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Loan> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Loan loan, Connection connection) {

    }

    @Override
    public void update(Loan loan, Connection connection) {

    }

    @Override
    public void linkEntities(Loan l, Book b) {

    }

    @Override
    public void unlinkEntities(Loan l, Book b) {

    }

    @Override
    public void linkEntities(Loan l, User u) {

    }

    @Override
    public void unlinkEntities(Loan l, User u) {

    }
}
