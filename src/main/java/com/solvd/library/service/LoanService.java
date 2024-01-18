package com.solvd.library.service;

import com.solvd.library.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> getLoanById(Long id);
    List<Loan> getAllLoans();
    Loan createLoan(Loan loan, Long userId, Long bookId);
    Loan updateLoan(Loan loan, Long userId, Long bookId);
    void deleteLoan(Long id);
}