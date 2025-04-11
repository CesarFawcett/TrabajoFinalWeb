package edu.unimagdalena.Dto;

import lombok.*;

@Data
public class FlightCreateDto {
    private int id;
    private String departureDate;
    private String departureAirportCode;
    private String departureCity;
    private String arrivalDate;
    private String arrivalAirportCode;
    private String arrivalCity;
    private int ticketPrice;
    private String ticketCurrency;
    private int flightNumber;
    private int seatCapacity;
}
