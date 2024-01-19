package com.solvd.library.service.impl;


import com.solvd.library.domain.Branch;
import com.solvd.library.persistence.BranchRepository;
import com.solvd.library.persistence.impl.BranchJDBCImpl;
import com.solvd.library.persistence.impl.BranchMybatisImpl;
import com.solvd.library.service.BranchService;
import com.solvd.library.service.EmployeeService;
import com.solvd.library.service.InventoryService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final InventoryService inventoryService = new InventoryServiceImpl();

    public BranchServiceImpl() {
        this.branchRepository = new BranchJDBCImpl();
        //this.branchRepository = new BranchMybatisImpl();
    }

    @Override
    public Optional<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }

    @Override
    public Collection<Branch> getAllBranchs() {
        return branchRepository.findAll();
    }

    @Override
    public Branch createBranch(Branch branch) {
        branch.setId(null);
        branchRepository.create(branch);
        return branch;
    }

    @Override
    public Branch updateBranch(Branch branch) {
        branchRepository.update(branch);
        return branch;
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.delete(id);
    }
}