package com.solvd.library.persistence;

import com.solvd.library.domain.Branch;
import com.solvd.library.domain.Employee;

public interface EmployeeRepoRelationalBranch {
    void linkEntity(Employee e, Branch br);
    void unlinkEntity(Employee e, Branch br);
}
