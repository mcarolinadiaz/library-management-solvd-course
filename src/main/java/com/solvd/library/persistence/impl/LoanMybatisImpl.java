package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Loan;
import com.solvd.library.persistence.LoanRepository;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.LoanRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class LoanMybatisImpl implements LoanRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.delete(id);
        }
    }

    @Override
    public Optional<Loan> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            return loanRepository.findById(id);
        }
    }

    @Override
    public Collection<Loan> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            return loanRepository.findAll();
        }
    }

    @Override
    public void create(Loan loan) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.create(loan);
        }
    }

    @Override
    public void update(Loan loan) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.update(loan);
        }
    }
}
