package com.example.book_my_show.Repos;

import com.example.book_my_show.models.showSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatTypeRepo extends JpaRepository<showSeatType,Integer> {
    List<showSeatType> findAllByShow(int showId);
}
