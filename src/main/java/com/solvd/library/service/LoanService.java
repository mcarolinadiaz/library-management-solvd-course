package com.solvd.library.service;

import com.solvd.library.domain.Loan;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> getLoanById(Long id);
    Collection<Loan> getAllLoans();
    Loan createLoan(Loan loan);
    Loan updateLoan(Loan loan);
    void deleteLoan(Long id);
}