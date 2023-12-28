package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Inventory;

import java.sql.Connection;
import java.util.List;

public interface BranchRepository extends GenericDAO<Branch>{
    Branch findById(Long id, Connection connection);
    List<Branch> findAll(Connection connection);
    void create(Branch branch, Connection connection);
    void update(Branch branch, Connection connection);

}
