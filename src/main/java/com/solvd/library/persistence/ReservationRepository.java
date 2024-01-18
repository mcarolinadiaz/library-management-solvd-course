package com.solvd.library.persistence;

import com.solvd.library.domain.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends GenericDAO<Reservation> {
    Optional<Reservation> findById(@Param("id") Long id);
    Collection<Reservation> findAll();
    void create(@Param("reservation") Reservation reservation);
    void update(@Param("reservation") Reservation reservation);
}
