package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Screen extends BaseModel{
    int capacity;
    @ManyToOne
    Theatres theatre;
    @OneToMany(mappedBy = "screen")
    List<Seat> seats;
}
