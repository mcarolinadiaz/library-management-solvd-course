package com.solvd.library.service;

import com.solvd.library.domain.Branch;

import java.util.List;

public interface BranchService {
    Branch getBranchById(Long id);
    List<Branch> getAllBranchs();
    Branch createBranch(Branch branch);
    Branch updateBranch(Branch branch);
    void deleteBranch(Long id);
}
