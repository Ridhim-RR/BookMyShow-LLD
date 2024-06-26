package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Movie extends BaseModel {
   private String name;
   @ManyToMany
    private List<Director> directors;
   @ManyToMany
    private  List<Actor> actors;

    private String genre;
}
