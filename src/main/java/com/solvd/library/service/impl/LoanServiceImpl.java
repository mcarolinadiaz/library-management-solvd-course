package com.solvd.library.service.impl;


import com.solvd.library.domain.Loan;
import com.solvd.library.persistence.LoanRepository;
import com.solvd.library.persistence.impl.LoanJDBCImpl;
import com.solvd.library.persistence.impl.LoanMybatisImpl;
import com.solvd.library.service.LoanService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public LoanServiceImpl() {
        //this.loanRepository = new LoanJDBCImpl();
        this.loanRepository = new LoanMybatisImpl();
    }

    @Override
    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public Collection<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan createLoan(Loan loan) {
        loan.setId(null);
        loanRepository.create(loan);
        return loan;
    }

    @Override
    public Loan updateLoan(Loan loan) {
        loanRepository.update(loan);
        return loan;
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.delete(id);
    }
}
