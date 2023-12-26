package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;

import java.util.List;

public interface BranchRepository {
    Branch findById(int id);
    List<Branch> findAll();
    void save(Branch branch);
    void update(Branch branch);
    void delete(int id);
}
