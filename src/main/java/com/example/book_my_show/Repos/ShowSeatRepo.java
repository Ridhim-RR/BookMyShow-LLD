package com.example.book_my_show.Repos;

import com.example.book_my_show.models.showSeats;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepo extends JpaRepository<showSeats,Integer> {
    @Lock(value =  LockModeType.PESSIMISTIC_READ)
    List<showSeats> findAllByIdAndAndSeatIdsAndSeatStatus_Available(int showId, List<Integer> seatIds);
}
