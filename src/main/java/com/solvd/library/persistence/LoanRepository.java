package com.solvd.library.persistence;

import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Loan;

import java.sql.Connection;
import java.util.List;

public interface LoanRepository extends GenericDAO<Loan> {
    Loan findById(Long id, Connection connection);
    List<Loan> findAll(Connection connection);
    void create(Loan loan, Connection connection);
    void update(Loan loan, Connection connection);
}
