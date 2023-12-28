package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Inventory;

public interface BranchRepoContainsInventory {
    void addEntity(Branch branch, Inventory i);
    void deleteEntity(Branch branch, Inventory i);
}
