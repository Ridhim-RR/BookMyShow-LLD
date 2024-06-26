package com.example.book_my_show.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class showSeats extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private User user;
    @ManyToOne
    private Seat seat;
    @Enumerated(value = EnumType.ORDINAL)
    private seatStatus status;

}
