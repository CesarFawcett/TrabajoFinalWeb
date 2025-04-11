package edu.unimagdalena.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
   
    //@Query("SELECT b FROM Booking b JOIN b.customerName u WHERE u.username = :customerName AND b.status = :status")
    //List<Booking> findByStatusAndCustomerNameWithQuery(@Param("status") BookingStatus status, @Param("customerName") String customerName);
    List<Booking> findByStatus(BookingStatus status); 
    @Query("SELECT b FROM Booking b JOIN b.customerName u WHERE u.username = :customerName")
    List<Booking> findByCustomerName(@Param("customerName") String customerName);
    
    @Query("SELECT c FROM Booking c JOIN c.customerName u WHERE u.username = :customerName AND c.status = :status")
    List<Booking> findByStatusAndCustomerName(@Param("status") BookingStatus status,@Param("customerName") String customerName);
  
    @Query("SELECT t FROM Booking t JOIN t.outboundFlight f WHERE f.id = :flightId")
    List<Booking> findBookingByOutboundFlight(@Param("flightId") Integer flightId);

    //@Query("SELECT b FROM Booking b WHERE b.outboundFlight.id = :flightId")
    //List<Booking> findByOutboundFlightId(@Param("flightId") int flightId);    
}

