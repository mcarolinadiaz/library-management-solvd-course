package com.solvd.library.persistence;

import com.solvd.library.domain.Loan;

import java.util.List;

public interface LoanRepository {
    Loan findById(int id);
    List<Loan> findAll();
    void save(Loan loan);
    void update(Loan loan);
    void delete(int id);
}
