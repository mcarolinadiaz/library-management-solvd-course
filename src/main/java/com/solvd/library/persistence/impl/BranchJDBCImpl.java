package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.BranchRepoContainsEmployee;
import com.solvd.library.persistence.BranchRepoContainsInventory;
import com.solvd.library.persistence.BranchRepository;

import java.sql.Connection;
import java.util.List;

public class BranchJDBCImpl implements BranchRepository, BranchRepoContainsEmployee, BranchRepoContainsInventory {
    @Override
    public Branch findById(Long id, Connection connection) {
        return null;
    }

    @Override
    public List<Branch> findAll(Connection connection) {
        return null;
    }

    @Override
    public void create(Branch branch, Connection connection) {

    }

    @Override
    public void update(Branch branch, Connection connection) {

    }

    @Override
    public void delete(Long id, Connection connection) {

    }

    @Override
    public void addEntity(Branch branch, Employee e) {

    }

    @Override
    public void deleteEntity(Branch branch, Employee e) {

    }

    @Override
    public void addEntity(Branch branch, Inventory i) {

    }

    @Override
    public void deleteEntity(Branch branch, Inventory i) {

    }
}
