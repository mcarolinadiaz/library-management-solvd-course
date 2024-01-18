package com.solvd.library.persistence.impl;

import com.solvd.library.domain.Reservation;
import com.solvd.library.persistence.PersistenceConfig;
import com.solvd.library.persistence.ReservationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class ReservationMybatisImpl implements ReservationRepository {
    @Override
    public void delete(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
            reservationRepository.delete(id);
        }
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
            return reservationRepository.findById(id);
        }
    }

    @Override
    public Collection<Reservation> findAll() {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
            return reservationRepository.findAll();
        }
    }

    @Override
    public void create(Reservation reservation) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
            reservationRepository.create(reservation);
        }
    }

    @Override
    public void update(Reservation reservation) {
        try(SqlSession sqlSession = PersistenceConfig.getSqlSessionFactory().openSession(true)) {
            ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
            reservationRepository.update(reservation);
        }
    }
}
