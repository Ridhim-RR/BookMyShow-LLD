package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class Director extends BaseModel{
    String name;
    @ManyToMany(mappedBy = "directors")
    List<Movie> movies;
}
