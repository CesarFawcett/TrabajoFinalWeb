package edu.unimagdalena.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;
import edu.unimagdalena.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking createBooking(Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }
    
    @Override
    public List<Booking> findBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public List<Booking> findBookingsByStatusAndCustomerName(BookingStatus status, String customerName) {
    if (status == null) {
        throw new IllegalArgumentException("Booking status cannot be null");
    }
    if (customerName == null || customerName.trim().isEmpty()) {
        throw new IllegalArgumentException("Customer name cannot be null or empty");
    }
    return bookingRepository.findByStatusAndUser_Username(status, customerName);
    }

    @Override
    public List<Booking> findBookingsByCustomerName(String customerName) {
    if (customerName == null || customerName.trim().isEmpty()) {
        throw new IllegalArgumentException("Customer name cannot be null or empty");
    }
    return bookingRepository.findByUser_Username(customerName);
    }

    @Override
    public List<Booking> findBookingByOutboundFlight(Integer flightId) {
    if (flightId == null || flightId <= 0) {
        throw new IllegalArgumentException("Flight ID must be a positive number");
    }
    return bookingRepository.findByOutboundFlightId(flightId);
    }

   
}