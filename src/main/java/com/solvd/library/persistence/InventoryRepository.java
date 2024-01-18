package com.solvd.library.persistence;

import com.solvd.library.domain.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends GenericDAO<Inventory> {
    Optional<Inventory> findById(@Param("id") Long id);
    List<Inventory> findAll();
    void create(@Param("inventory") Inventory inventory, @Param("branchId") Long branchId,@Param("bookId") Long bookId);
    void update(@Param("inventory") Inventory inventory, @Param("branchId") Long branchId,@Param("bookId") Long bookId);
}
