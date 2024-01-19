package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

public interface BranchRepository extends GenericDAO<Branch>{
    Optional<Branch> findById(@Param("id") Long id);
    Collection<Branch> findAll();
    void create(@Param("branch") Branch branch);
    void update(@Param("branch") Branch branch);

}
