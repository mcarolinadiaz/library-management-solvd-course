package com.solvd.library.persistence;

import com.solvd.library.domain.Loan;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends GenericDAO<Loan> {
    Optional<Loan> findById(@Param("id") Long id);
    Collection<Loan> findAll();
    void create(@Param("loan") Loan loan);
    void update(@Param("loan") Loan loan);
}
