package com.solvd.library.persistence.impl;

import com.solvd.library.persistence.BranchRepository;

public interface RepositoryFactory {
    BranchRepository createBranchRepository();

}

