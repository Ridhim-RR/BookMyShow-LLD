package com.example.book_my_show.Services;

import com.example.book_my_show.Exceptions.InvalidRequestException;
import com.example.book_my_show.Exceptions.UnavailableSeatException;
import com.example.book_my_show.Repos.*;
import com.example.book_my_show.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ticketServiceImp implements ticketService{
//    Check if the User is valid or not.
//    Check if the show id valid or not as we can allow the user to book ticket 15 mins after the start time of the show.
//    No loop over the seats to see whether they are available or not.
//    if yes then fetch available show-seats from the DB.
//    If available seat count == all the seats in the request
//    Update the seat status to blocked
//    else throw exception  seats are unavailable
//    if not then rollback the entire query
    private UserRepo userRepo;
    private ShowRepo showRepo;
    private SeatRepo seatRepo;
    private ShowSeatRepo showSeat;
    private ShowSeatTypeRepo showSeatTypeRepo;
    private TicketRepo ticketRepo;
    @Autowired
    public ticketServiceImp(UserRepo userRepo, ShowRepo showRepo, SeatRepo seatRepo, ShowSeatRepo showSeat, ShowSeatTypeRepo showSeatTypeRepo, TicketRepo ticketRepo){
        this.userRepo = userRepo;
        this.showRepo = showRepo;
        this.seatRepo = seatRepo;
        this.showSeat = showSeat;
        this.showSeatTypeRepo = showSeatTypeRepo;
        this.ticketRepo = ticketRepo;
    }
    @Override
    public Ticket bookTicket(List<Integer> seats, int user, int show) throws InvalidRequestException, UnavailableSeatException {
        Optional<User> userId = this.userRepo.findUserById(user);
        if(userId.isEmpty()){
            throw new InvalidRequestException("User is invalid");
        }
        User userObj = userId.get();
        Optional<Show> showId = this.showRepo.findShowById(show);
        if(showId.isEmpty()){
            throw  new InvalidRequestException("Show is not valid");
        }
        long currentTime = new Date().getTime();
        Show showObj = showId.get();
        if(showObj.getStartTime().getTime() + (10 * 60L) < currentTime){
            throw new InvalidRequestException("This show cannot be booked");
        }
        List<Seat> Seats = this.seatRepo.findAllByIdIn(seats);
        if(Seats.size() != seats.size()){
          throw new InvalidRequestException("Seats are invalid");
        }
//       TODO::: Check if the seats belong to the screen on which the show is going to run:::
        blockSeatForUser(userObj,showObj,seats);

//        Calculate the Amount:::
//        We need to fetch the type of tickets and fetch their price;
        double totalAmt = 0;
        List<showSeatType> showSeatTypes = this.showSeatTypeRepo.findAllByShow(show);
        Map<SeatType, Double> pricingMap = new HashMap<>();
        for(showSeatType seatType : showSeatTypes){
            pricingMap.put(seatType.getSeatType(), seatType.getAmount());
        }
        for(Seat seat : Seats){
            totalAmt = totalAmt + pricingMap.get(seat.getSeatType());
        }
        // TODO: Apply strategy pattern to compute convenience fee and add it to the total amount

        Ticket ticket = new Ticket();
        ticket.setTicketStatus(ticketStatus.UNPAID);
        ticket.setUser(userObj);
        ticket.setShow(showObj);
        ticket.setAmount(totalAmt);
        return  this.ticketRepo.save(ticket);
    }



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void blockSeatForUser(User user, Show show, List<Integer> seatIds) throws UnavailableSeatException{
//        int showId, List<Integer> seatIds
        int showId = show.getId();
        List<showSeats> showSeats = this.showSeat.findAllByIdAndAndSeatIdsAndSeatStatus_Available(showId,seatIds);
        if(showSeats.size() != seatIds.size()){
            throw new UnavailableSeatException("Seats are not available");
        }
        showSeats.stream().forEach((s) -> {
            s.setStatus(seatStatus.BLOCKED);
            s.setUser(user);
        });
        showSeat.saveAll(showSeats);
    }
}
