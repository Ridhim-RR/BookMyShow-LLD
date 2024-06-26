package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Actor extends BaseModel{
    private String name;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

}
