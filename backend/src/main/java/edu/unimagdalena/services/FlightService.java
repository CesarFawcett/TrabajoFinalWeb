package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.entities.Flight;


public interface FlightService {
    void delete(Integer id);
    Optional<Flight> fainId(Integer id);
    Optional<Flight> updateFlight(Integer id, Flight flightToUpdate);
    Flight createFlight(Flight newFlight);
    List<Flight> findFlightsByDepartureDate(String departureDate);
    List<Flight> findFlightsByDepartureDateAndDepartureAirportCode(String departureDate,
            String departureAirportCode);
    List<Flight> findFlightsByDepartureDateAndArrivalAirPortCode(String departureDate, String arrivalAirportCode);
    List<Flight> findFlightsByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,
            String departureAirportCode, String arrivalAirportCode);
    List<Flight> findFlightsByArrivalAirportCodeAndDepartureAirporCode(String arrivalAirportCode,
                String departureAirportCode);
    Flight getFlightById(int flightId);
    List<Flight> findByDepartureAirportCodeAndDepartureDate(String departureAirportCode, String departureDate);
    List<Flight> findAll();
}

