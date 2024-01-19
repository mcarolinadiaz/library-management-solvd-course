package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Branch;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.BranchRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class BranchMybatisImpl implements BranchRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.delete(id);
        }
    }

    @Override
    public Optional<Branch> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            return branchRepository.findById(id);
        }
    }

    @Override
    public Collection<Branch> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            return branchRepository.findAll();
        }
    }

    @Override
    public void create(Branch branch) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.create(branch);
        }
    }

    @Override
    public void update(Branch branch) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.update(branch);
        }
    }
}
