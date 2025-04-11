package edu.unimagdalena.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import edu.unimagdalena.Dto.BookingCreateDto;
import edu.unimagdalena.Dto.BookingDto;
import edu.unimagdalena.Dto.BookingMapper;
import edu.unimagdalena.entities.Booking;
import edu.unimagdalena.entities.BookingStatus;
import edu.unimagdalena.entities.User;
import edu.unimagdalena.exceptions.BookingNotFoundException;
import edu.unimagdalena.exceptions.DuplicateCodigoException;
import edu.unimagdalena.exceptions.FlightNotFoundException;
import edu.unimagdalena.services.BookingService;
import edu.unimagdalena.services.UserService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private  BookingMapper bookingMapper;
    @Autowired
    private UserService userService;

    //Crear 
    @PostMapping("")
    public ResponseEntity<BookingCreateDto>create(@RequestBody BookingCreateDto booking){ 
        Booking newBooking = bookingMapper.toEntity(booking);
        Booking bookingCreate = null;
         try {
            bookingCreate = bookingService.createBooking(newBooking);
         } catch (Exception e) {
             throw new DuplicateCodigoException();
         }
         BookingCreateDto bookingCreateDto = bookingMapper.toBookingCreateDto(bookingCreate);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                         .path("/{id}")
                         .buildAndExpand(bookingCreateDto.getId())
                         .toUri();
         return ResponseEntity.created(location).body(bookingCreateDto);
    }

    //1 Obtiene una reserva por el id
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto>findById(@PathVariable("id") Integer id){
        BookingDto booking= bookingService.findById(id)
         .map(t -> bookingMapper.toDto(t))
         .orElseThrow(BookingNotFoundException::new);
     return ResponseEntity.status(HttpStatus.FOUND).body(booking);
    }

    //2 Obtiene un listado de reserva según el parámetro de búsqueda indicado, puede ser sin parámetro, un
    // sólo parámetro o los dos parámetros a la vez por status y customerName
    @GetMapping("/")
    public ResponseEntity<List<BookingDto>> findBookingsByStatusAndCustomerName(
                                            @RequestParam(name = "status", required = false) BookingStatus status,
                                            @RequestParam(name = "customerName", required = false) String customerName) {
        List<Booking> bookings;
        if (status != null && customerName != null) {
          bookings = bookingService.findBookingsByStatusAndCustomerName(status, customerName);
           } else if (status == null && customerName == null) {
             bookings = bookingService.findAll();
           } else if (customerName == null) {
          bookings = bookingService.findBookingsByStatus(status);
           } else {
          bookings = bookingService.findBookingsByCustomerName(customerName);
         }
        List<BookingDto> bookingDtos = bookings.stream()
        .map(BookingMapper::fromBooking)
        .collect(Collectors.toList());
      return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
    }

    //3 Permite crear una reserva de un vuelo y un usuario dado en el path parámetro
    @PostMapping("/flight/{flighId}/user/{userId}")
    public ResponseEntity<BookingCreateDto> createBooking(@RequestParam int userId, @RequestParam int flightId) {
    User user = userService.getUserById(userId);
    Booking newBooking = new Booking();
    newBooking.setStatus(BookingStatus.CONFIRMED);
    newBooking.setCheckedIn(true);
    newBooking.setUser(user);
    Booking bookingCreated = null;
    try {
        bookingCreated = bookingService.createBooking(newBooking);
    } catch (Exception e) {
        throw new DuplicateCodigoException();
    }
    BookingCreateDto bookingCreateDto = bookingMapper.toBookingCreateDto(bookingCreated);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                     .path("/{id}")
                     .buildAndExpand(bookingCreateDto.getId())
                     .toUri();
    return ResponseEntity.created(location).body(bookingCreateDto);
    }

    //4 Elimina una reserva por el id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //5 Obtiene todas las reservas para un vuelo especificado
    @GetMapping("/flight/{flight_Id}")
    public ResponseEntity<List<BookingDto>> findBookingsByFlightId(@PathVariable(name = "flight_Id") Integer flight_Id) {
    List<Booking> bookings;
    if (flight_Id != null) {
        bookings = bookingService.findBookingByOutboundFlight(flight_Id);
    } else {
        throw new FlightNotFoundException();
    }
    List<BookingDto> bookingDtos = bookings.stream()
        .map(BookingMapper::fromBooking)
        .collect(Collectors.toList());
    return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
   }   
}

