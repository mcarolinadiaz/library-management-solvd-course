package com.solvd.library.persistence;

import com.solvd.library.domain.Loan;

import java.sql.Connection;
import java.util.List;

public interface LoanRepository extends GenericDAO<Loan> {
    Loan findById(Long id);
    List<Loan> findAll();
    void create(Loan loan, Long userId, Long bookId);
    void update(Loan loan, Long userId, Long bookId);
}
