package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Inventory;
import com.solvd.library.persistence.InventoryRepository;
import com.solvd.library.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class InventoryMybatisImpl implements InventoryRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            InventoryRepository inventoryRepository = sqlSession.getMapper(InventoryRepository.class);
            inventoryRepository.delete(id);
        }
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            InventoryRepository inventoryRepository = sqlSession.getMapper(InventoryRepository.class);
            return inventoryRepository.findById(id);
        }
    }

    @Override
    public Collection<Inventory> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            InventoryRepository inventoryRepository = sqlSession.getMapper(InventoryRepository.class);
            return inventoryRepository.findAll();
        }
    }

    @Override
    public void create(Inventory inventory) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            InventoryRepository inventoryRepository = sqlSession.getMapper(InventoryRepository.class);
            inventoryRepository.create(inventory);
        }
    }

    @Override
    public void update(Inventory inventory) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            InventoryRepository inventoryRepository = sqlSession.getMapper(InventoryRepository.class);
            inventoryRepository.update(inventory);
        }
    }
    
}
