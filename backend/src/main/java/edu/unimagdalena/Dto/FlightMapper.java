package edu.unimagdalena.Dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import edu.unimagdalena.entities.Flight;

@Component
public class FlightMapper {
    public FlightCreateDto toFlightCreateDto(Flight flight){
        FlightCreateDto flightCreateDto = new FlightCreateDto();
        flightCreateDto.setId(flight.getId());
        flightCreateDto.setDepartureDate(flight.getDepartureDate());
        flightCreateDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightCreateDto.setDepartureCity(flight.getDepartureCity());
        flightCreateDto.setArrivalDate(flight.getArrivalDate());
        flightCreateDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightCreateDto.setArrivalCity(flight.getArrivalCity());
        flightCreateDto.setTicketPrice(flight.getTicketPrice());
        flightCreateDto.setTicketCurrency(flight.getTicketCurrency());
        flightCreateDto.setFlightNumber(flight.getFlightNumber());
        flightCreateDto.setSeatCapacity(flight.getSeatCapacity());
        return flightCreateDto;
    }
    public Flight toFlight(FlightCreateDto flightCreateDto) {
        Flight flight = new Flight();
        flight.setId(flightCreateDto.getId());
        flight.setDepartureDate(flightCreateDto.getDepartureDate());
        flight.setDepartureAirportCode(flightCreateDto.getDepartureAirportCode());
        flight.setDepartureCity(flightCreateDto.getDepartureCity());
        flight.setArrivalDate(flightCreateDto.getArrivalDate());
        flight.setArrivalAirportCode(flightCreateDto.getArrivalAirportCode());
        flight.setArrivalCity(flightCreateDto.getArrivalCity());
        flight.setTicketPrice(flightCreateDto.getTicketPrice());
        flight.setTicketCurrency(flightCreateDto.getTicketCurrency());
        flight.setFlightNumber(flightCreateDto.getFlightNumber());
        flight.setSeatCapacity(flightCreateDto.getSeatCapacity());
        return flight;
    }
    public static List<FlightDto> toDtoList(List<Flight> flights) {
       List<FlightDto> flightDtos = new ArrayList<>();
    for (Flight flight : flights) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setDepartureDate(flight.getDepartureDate());
        flightDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightDto.setDepartureCity(flight.getDepartureCity());
        flightDto.setArrivalDate(flight.getArrivalDate());
        flightDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightDto.setArrivalCity(flight.getArrivalCity());
        flightDto.setTicketPrice(flight.getTicketPrice());
        flightDto.setTicketCurrency(flight.getTicketCurrency());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setSeatCapacity(flight.getSeatCapacity());
        
        flightDtos.add(flightDto);
    }
    return flightDtos;
    }
    public Flight toEntity(FlightCreateDto flight2) {
        Flight flight = new Flight();
        flight.setId(flight2.getId());
        flight.setDepartureDate(flight2.getDepartureDate());
        flight.setDepartureAirportCode(flight2.getDepartureAirportCode());
        flight.setDepartureCity(flight2.getDepartureCity());
        flight.setArrivalDate(flight2.getArrivalDate());
        flight.setArrivalAirportCode(flight2.getArrivalAirportCode());
        flight.setArrivalCity(flight2.getArrivalCity());
        flight.setTicketPrice(flight2.getTicketPrice());
        flight.setTicketCurrency(flight2.getTicketCurrency());
        flight.setFlightNumber(flight2.getFlightNumber());
        flight.setSeatCapacity(flight2.getSeatCapacity());
        return flight;
    }
    public static FlightDto fromFlight(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setDepartureDate(flight.getDepartureDate());
        flightDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightDto.setDepartureCity(flight.getDepartureCity());
        flightDto.setArrivalDate(flight.getArrivalDate());
        flightDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightDto.setArrivalCity(flight.getArrivalCity());
        flightDto.setTicketPrice(flight.getTicketPrice());
        flightDto.setTicketCurrency(flight.getTicketCurrency());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setSeatCapacity(flight.getSeatCapacity());
        return flightDto;
    }
    public FlightDto toFlightDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setDepartureDate(flight.getDepartureDate());
        flightDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightDto.setDepartureCity(flight.getDepartureCity());
        flightDto.setArrivalDate(flight.getArrivalDate());
        flightDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightDto.setArrivalCity(flight.getArrivalCity());
        flightDto.setTicketPrice(flight.getTicketPrice());
        flightDto.setTicketCurrency(flight.getTicketCurrency());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setSeatCapacity(flight.getSeatCapacity());
        return flightDto;
    }
}