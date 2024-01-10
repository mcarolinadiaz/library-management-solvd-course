package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Inventory;

import java.sql.Connection;
import java.util.List;

public interface BranchRepository extends GenericDAO<Branch>{
    Branch findById(Long id);
    List<Branch> findAll();
    void create(Branch branch);
    void update(Branch branch);

}
