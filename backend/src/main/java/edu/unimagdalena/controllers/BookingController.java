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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "API para gestión de reservas de vuelos")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private  BookingMapper bookingMapper;
    @Autowired
    private UserService userService;
    
    @Operation(summary = "Crear reserva", description = "Crea una nueva reserva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
        @ApiResponse(responseCode = "409", description = "Código de reserva duplicado")
    })
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

    @Operation(summary = "Obtener reserva por ID")
    @ApiResponse(responseCode = "302", description = "Reserva encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto>findById(@PathVariable("id") Integer id){
        BookingDto booking= bookingService.findById(id)
         .map(t -> bookingMapper.toDto(t))
         .orElseThrow(BookingNotFoundException::new);
     return ResponseEntity.status(HttpStatus.FOUND).body(booking);
    }

    @Operation(summary = "Buscar reservas", description = "Filtra reservas por estado y/o nombre de cliente")
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

    @Operation(summary = "Crear reserva para usuario y vuelo", 
           description = "Crea una reserva confirmada con check-in automático")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
    @ApiResponse(responseCode = "404", description = "Usuario o vuelo no encontrado"),
    @ApiResponse(responseCode = "409", description = "Código de reserva duplicado")
    })
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

    @Operation(summary = "Eliminar reserva", 
           description = "Elimina una reserva por su ID")
    @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
    @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener reservas por vuelo", 
           description = "Lista todas las reservas asociadas a un vuelo específico")
    @ApiResponse(responseCode = "200", description = "Lista de reservas encontradas")
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

