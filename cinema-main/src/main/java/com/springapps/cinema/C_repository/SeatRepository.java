package com.springapps.cinema.C_repository;

import com.springapps.cinema.D_model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {


    public Optional<Seat> findSeatBySeatRowAndSeatColumnAndCinemaRoom_Id(Integer row, Integer col, Long cinemaRoomId);
}
