package com.example.book_my_show.Services;

import com.example.book_my_show.Exceptions.InvalidRequestException;
import com.example.book_my_show.Exceptions.UnavailableSeatException;
import com.example.book_my_show.models.Seat;
import com.example.book_my_show.models.Show;
import com.example.book_my_show.models.Ticket;
import com.example.book_my_show.models.User;

import java.util.List;

public interface ticketService {
//    Ticket service will deal with all the operations regarding the tickets like book, delete etc..
    public Ticket bookTicket(List<Integer> seats, int user, int show) throws InvalidRequestException, UnavailableSeatException;
}
