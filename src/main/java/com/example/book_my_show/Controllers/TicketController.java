package com.example.book_my_show.Controllers;

import com.example.book_my_show.DTOs.BookTicketRequestDto;
import com.example.book_my_show.DTOs.BookTicketResponseDto;
import com.example.book_my_show.DTOs.ResponseType;
import com.example.book_my_show.Exceptions.InvalidRequestException;
import com.example.book_my_show.Exceptions.UnavailableSeatException;
import com.example.book_my_show.Services.ticketService;
import com.example.book_my_show.Services.ticketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
//Job of  the ticket controller is take requestDto from the client , validate it.
//    If the data is valid then send the data send the data to the ticketservice.
//    As the no two concrete class should be directly dependent on each other , they should be dependent on abstraction
    private ticketService ticketService;
    @Autowired
    public TicketController(ticketService ticketService){
        this.ticketService = ticketService;
    }
    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto) throws InvalidRequestException, UnavailableSeatException {
      BookTicketResponseDto responseDto = new BookTicketResponseDto();
     this.ticketService.bookTicket(requestDto.getSeatIds(),requestDto.getUserId(), requestDto.getShowId());
      try{
      validateRequest(requestDto);
      }catch (Exception e){
      responseDto.setResponseType(ResponseType.FAILURE);
      responseDto.setErrorMsg(e.getMessage());
      }
      return responseDto;
    }

    private void validateRequest(BookTicketRequestDto requestDto) throws InvalidRequestException{
        if(requestDto.getSeatIds() == null || requestDto.getSeatIds().isEmpty()){
            throw new InvalidRequestException("SeatIds should be present");
        }
        if(requestDto.getUserId() < 0){
            throw new InvalidRequestException("User id is invalid");
        }
        if(requestDto.getShowId() < 0){
            throw new InvalidRequestException("Show Id is invalid");
        }
    }
}
