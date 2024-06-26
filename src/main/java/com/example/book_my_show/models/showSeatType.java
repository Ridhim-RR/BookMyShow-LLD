package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class showSeatType extends BaseModel {
    private SeatType seatType;
    private Show show;
    private double amount;
}
