package com.solvd.library.persistence.impl;

import com.solvd.library.persistence.BranchRepository;

public class MybatisRepositoryFactory implements RepositoryFactory {
    @Override
    public BranchRepository createBranchRepository() {
        return new BranchMybatisImpl();
    }
}
