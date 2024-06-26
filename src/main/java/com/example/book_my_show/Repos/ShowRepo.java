package com.example.book_my_show.Repos;

import com.example.book_my_show.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowRepo extends JpaRepository<Show,Integer> {
    Optional<Show> findShowById(int id);
}
