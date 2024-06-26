package com.example.book_my_show.DTOs;

import lombok.Data;

import java.util.List;
@Data
public class BookTicketRequestDto {
    private List<Integer> seatIds;
    private int showId;
    private int userId;
}
