package edu.unimagdalena.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.entities.Flight;



public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT p FROM Flight p WHERE p.departureDate = :departureDate ")
    List<Flight> findByDepartureDate(String departureDate);
    
    @Query("SELECT t FROM Flight t WHERE t.departureDate = :departureDate AND t.departureAirportCode = :departureAirportCode AND t.arrivalAirportCode = :arrivalAirportCode")
    List<Flight> findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,String departureAirportCode, String arrivalAirportCode);
    
    @Query("SELECT c FROM Flight c WHERE c.departureDate = :departureDate AND c.departureAirportCode = :departureAirportCode")
    List<Flight> findByDepartureDateAndDepartureAirportCode(String departureDate, String departureAirportCode);
    
    @Query("SELECT d FROM Flight d WHERE d.departureDate = :departureDate AND d.arrivalAirportCode = :arrivalAirportCode")
    List<Flight> findByDepartureDateAndArrivalAirPortCode(String departureDate, String arrivalAirportCode);
    
    @Query("SELECT y FROM Flight y WHERE y.departureAirportCode = :departureAirportCode AND y.departureDate = :departureDate")
    List<Flight> findByAirportCodeAndDepartureDate(String departureAirportCode, String departureDate);
    
    @Query("SELECT o FROM Flight o WHERE o.arrivalAirportCode = :arrivalAirportCode AND o.departureAirportCode = :departureAirportCode")
    List<Flight> findByArrivalAirportCodeAndDepartureAirportCode(String arrivalAirportCode,
            String departureAirportCode);
    
    @Query("SELECT u FROM Flight u WHERE u.departureAirportCode = :departureAirportCode AND u.departureDate = :departureDate") 
    List<Flight> findFlightByDepartureDateAndDepartureAirportCode(String departureDate,
                    String departureAirportCode);

 
}

