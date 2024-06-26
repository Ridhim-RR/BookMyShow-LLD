package com.example.book_my_show.Repos;

import com.example.book_my_show.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
    List<Seat> findAllByIdIn(List<Integer> seatIds);

}
