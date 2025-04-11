package edu.unimagdalena.Dto;
import java.util.Optional;
import org.springframework.stereotype.Component;

import edu.unimagdalena.entities.Booking;

@Component
public class BookingMapper {
    public BookingCreateDto toBookingCreateDto(Booking booking){
        BookingCreateDto bookingCreateDto = new BookingCreateDto();
        bookingCreateDto.setId(booking.getId());
        bookingCreateDto.setStatus(booking.getStatus());
        bookingCreateDto.setCheckedIn(booking.isCheckedIn());
        bookingCreateDto.setUser(booking.getUser());
        bookingCreateDto.setBookingReference(booking.getBookingReference());
        return bookingCreateDto;
    }
    public BookingDto toDto(Booking booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setCheckedIn(booking.isCheckedIn());
        bookingDto.setBookingReference(booking.getBookingReference());
        return bookingDto;
    }
    public Booking toBooking(BookingCreateDto bookingCreateDto) {
        Booking booking = new Booking();
        booking.setId(bookingCreateDto.getId());
        booking.setStatus(bookingCreateDto.getStatus());
        booking.setCheckedIn(bookingCreateDto.isCheckedIn());
        booking.setBookingReference(bookingCreateDto.getBookingReference());
        return booking;
    }
    public BookingDto toDto(Optional<Booking> optionalBooking) {
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            BookingDto bookingDto = new BookingDto();
            bookingDto.setId(booking.getId());
            bookingDto.setStatus(booking.getStatus());
            bookingDto.setCheckedIn(booking.isCheckedIn());
            bookingDto.setUser(booking.getUser());
            bookingDto.setBookingReference(booking.getBookingReference());
            return bookingDto;
        } else {
            return null;
        }
    }
    public static BookingDto fromBooking(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setCheckedIn(booking.isCheckedIn());
        bookingDto.setUser(booking.getUser());
        bookingDto.setBookingReference(booking.getBookingReference());
        return bookingDto;
    }
    public Booking toEntity(BookingCreateDto bookingCreateDto) {
        Booking booking = new Booking();
        booking.setId(bookingCreateDto.getId());
        booking.setStatus(bookingCreateDto.getStatus());
        booking.setCheckedIn(bookingCreateDto.isCheckedIn());
        booking.setUser(bookingCreateDto.getUser());
        booking.setBookingReference(bookingCreateDto.getBookingReference());
        return booking;
    }
}
