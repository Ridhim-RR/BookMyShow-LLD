package com.example.book_my_show.DTOs;

import com.example.book_my_show.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDto {
    private Ticket ticket;
    private String errorMsg;
    private ResponseType responseType;


}
