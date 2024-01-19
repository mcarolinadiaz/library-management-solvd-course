package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Employee;
import com.solvd.library.domain.Employee;
import com.solvd.library.persistence.EmployeeRepository;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.EmployeeRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EmployeeMybatisImpl implements EmployeeRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.delete(id);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findById(id);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        }
    }

    @Override
    public void create(Employee employee) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        }
    }

    @Override
    public void update(Employee employee) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.update(employee);
        }
    }
}
