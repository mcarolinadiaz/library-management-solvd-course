package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Inventory;

public interface InventoryRepoContainsBranch {
    void linkEntities(Inventory i, Branch br);
    void unlinkEntities(Inventory i, Branch br);
}
