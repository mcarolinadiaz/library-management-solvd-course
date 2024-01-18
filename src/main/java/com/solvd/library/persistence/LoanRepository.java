package com.solvd.library.persistence;

import com.solvd.library.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends GenericDAO<Loan> {
    Optional<Loan> findById(Long id);
    List<Loan> findAll();
    void create(Loan loan, Long userId, Long bookId);
    void update(Loan loan, Long userId, Long bookId);
}
