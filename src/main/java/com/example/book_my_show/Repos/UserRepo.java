package com.example.book_my_show.Repos;

import com.example.book_my_show.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
    public interface UserRepo extends JpaRepository<User,Integer> {
        Optional<User> findUserById(int id);
        //    find -> Select
    //    user -> userTable
    //    by id -> where id = {id}
    }
