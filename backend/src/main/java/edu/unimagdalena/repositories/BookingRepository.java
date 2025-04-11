package edu.unimagdalena.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    
    // Consulta por estado
    List<Booking> findByStatus(BookingStatus status);
    
    // Consulta por username del usuario (usando relación)
    @Query("SELECT b FROM Booking b JOIN b.user u WHERE u.username = :username")
    List<Booking> findByUserUsername(@Param("username") String username);
    
    // Consulta por estado y username
    @Query("SELECT b FROM Booking b JOIN b.user u WHERE u.username = :username AND b.status = :status")
    List<Booking> findByStatusAndUserUsername(@Param("status") BookingStatus status, 
                                           @Param("username") String username);
  
    // Consulta por vuelo de salida
    @Query("SELECT b FROM Booking b WHERE b.outboundFlight.id = :flightId")
    List<Booking> findByOutboundFlightId(@Param("flightId") Integer flightId);
    
    // Consultas derivadas alternativas
    List<Booking> findByUser_Username(String username);
    List<Booking> findByStatusAndUser_Username(BookingStatus status, String username);
}

