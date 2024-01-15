package com.solvd.library.service.impl;


import com.solvd.library.domain.Loan;
import com.solvd.library.persistence.LoanRepository;
import com.solvd.library.persistence.impl.LoanJDBCImpl;
import com.solvd.library.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public LoanServiceImpl() {
        this.loanRepository = new LoanJDBCImpl();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan createLoan(Loan loan, Long userId, Long bookId) {
        loan.setId(null);
        loanRepository.create(loan, userId, bookId);
        return loan;
    }

    @Override
    public Loan updateLoan(Loan loan, Long userId, Long bookId) {
        loanRepository.update(loan, userId, bookId);
        return loan;
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.delete(id);
    }
}
