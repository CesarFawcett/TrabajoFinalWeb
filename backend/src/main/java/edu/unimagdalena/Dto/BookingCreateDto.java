package edu.unimagdalena.Dto;

import edu.unimagdalena.entities.BookingStatus;
import edu.unimagdalena.entities.Flight;
import edu.unimagdalena.entities.User;
import lombok.*;

@Data
public class BookingCreateDto {
    private int id;
    private BookingStatus status;
    private Flight utboundFlight;
    private String paymentToken;
    private boolean checkedIn;
    private User customer;
    private String createdAt;
    private String bookingReference;
}
