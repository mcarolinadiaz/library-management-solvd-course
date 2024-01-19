package com.solvd.library.service;

import com.solvd.library.domain.Branch;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BranchService {
    Optional<Branch> getBranchById(Long id);
    Collection<Branch> getAllBranchs();
    Branch createBranch(Branch branch);
    Branch updateBranch(Branch branch);
    void deleteBranch(Long id);
}
