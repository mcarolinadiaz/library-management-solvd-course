package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;

public interface BranchRepoContainsEmployee {
    void addEntity(Branch branch, Employee e);
    void deleteEntity(Branch branch, Employee e);
}
