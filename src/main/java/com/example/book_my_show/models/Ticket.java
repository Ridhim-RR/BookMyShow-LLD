package com.example.book_my_show.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Ticket extends BaseModel{
    private double amount;
    @Enumerated(value = EnumType.ORDINAL)
    private ticketStatus ticketStatus;

    @ManyToOne
    private Show show;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Seat> seats;
}
