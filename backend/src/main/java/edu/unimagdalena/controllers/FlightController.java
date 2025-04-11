package edu.unimagdalena.controllers;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.unimagdalena.Dto.FlightCreateDto;
import edu.unimagdalena.Dto.FlightDto;
import edu.unimagdalena.Dto.FlightMapper;
import edu.unimagdalena.entities.Flight;
import edu.unimagdalena.exceptions.DuplicateCodigoException;
import edu.unimagdalena.exceptions.FlightNotFoundException;
import edu.unimagdalena.services.FlightService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/catalog")
public class FlightController {
    
    private final FlightService flightService;
    private final FlightMapper flightMapper;
    
    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @PostConstruct
    public void initSampleFlights() {
        // Vuelo 1
        Flight flight1 = new Flight();
        flight1.setDepartureDate("2022-04-29");
        flight1.setDepartureAirportCode("LON");
        flight1.setDepartureAirportName("Airport 1");
        flight1.setDepartureCity("City 1");
        flight1.setDepartureLocale("Locale 1");
        flight1.setArrivalDate("2023-05-21");
        flight1.setArrivalAirportCode("ARG");
        flight1.setArrivalAirportName("Airport 2");
        flight1.setArrivalCity("City 2");
        flight1.setArrivalLocale("Locale 2");
        flight1.setTicketPrice(100);
        flight1.setTicketCurrency("USD");
        flight1.setFlightNumber(123);
        flight1.setSeatCapacity(200);
        flightService.createFlight(flight1);

        // Vuelo 2
        Flight flight2 = new Flight();
        flight2.setDepartureDate("2022-04-20");
        flight2.setDepartureAirportCode("MAD");
        flight2.setDepartureAirportName("Airport 3");
        flight2.setDepartureCity("City 2");
        flight2.setDepartureLocale("Locale 1");
        flight2.setArrivalDate("2023-04-25");
        flight2.setArrivalAirportCode("SIR");
        flight2.setArrivalAirportName("Airport 4");
        flight2.setArrivalCity("City 2");
        flight2.setArrivalLocale("Locale 2");
        flight2.setTicketPrice(150);
        flight2.setTicketCurrency("USD");
        flight2.setFlightNumber(123);
        flight2.setSeatCapacity(200);
        flightService.createFlight(flight2);
    }

    @GetMapping("/")
    public ResponseEntity<List<FlightDto>> findAll() {
        List<Flight> flights = flightService.findAll();
        List<FlightDto> flightDtos = flights.stream()
                .map(flightMapper::toFlightDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flightDtos);
    }

    @GetMapping("")
    public ResponseEntity<List<FlightDto>> findFlights(
            @RequestParam(required = false) String departureDate,
            @RequestParam(required = false) String departureAirportCode,
            @RequestParam(required = false) String arrivalAirportCode) {   
            List<Flight> flights;
        if (departureDate != null && departureAirportCode != null && arrivalAirportCode != null) {
            flights = flightService.findFlightsByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(
                    departureDate, departureAirportCode, arrivalAirportCode);
        } else if (departureDate != null && departureAirportCode != null) {
            flights = flightService.findFlightsByDepartureDateAndDepartureAirportCode(
                    departureDate, departureAirportCode);
        } else if (departureDate != null && arrivalAirportCode != null) {
            flights = flightService.findFlightsByDepartureDateAndArrivalAirPortCode(
                    departureDate, arrivalAirportCode);
        } else if (arrivalAirportCode != null && departureAirportCode != null) {
            flights = flightService.findFlightsByArrivalAirportCodeAndDepartureAirporCode(
                    arrivalAirportCode, departureAirportCode);
        } else {
            flights = flightService.findAll();
        }
        List<FlightDto> flightDtos = flights.stream()
                .map(flightMapper::toFlightDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flightDtos);
    }

    @PostMapping("/")
    public ResponseEntity<FlightCreateDto> createFlight(@RequestBody FlightCreateDto flightDto) {
        Flight newFlight = flightMapper.toEntity(flightDto);
        try {
            Flight flightCreated = flightService.createFlight(newFlight);
            FlightCreateDto responseDto = flightMapper.toFlightCreateDto(flightCreated);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(responseDto.getId())
                    .toUri();
            return ResponseEntity.created(location).body(responseDto);
        } catch (Exception e) {
            throw new DuplicateCodigoException();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightCreateDto> updateFlight(
            @PathVariable Integer id,
            @RequestBody FlightCreateDto flightDto) {
        
        Flight flightToUpdate = flightMapper.toEntity(flightDto);
        Optional<Flight> updatedFlight = flightService.updateFlight(id, flightToUpdate);
        
        if (updatedFlight.isPresent()) {
            FlightCreateDto updatedDto = flightMapper.toFlightCreateDto(updatedFlight.get());
            return ResponseEntity.ok(updatedDto);
        } else {
            throw new FlightNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer id) {
        flightService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{departureAirportCode}/")
    public ResponseEntity<List<FlightDto>> findFlightsByAirportAndDate(
            @PathVariable String departureAirportCode,
            @RequestParam String departureDate) {
        
        List<Flight> flights = flightService.findByDepartureAirportCodeAndDepartureDate(
                departureAirportCode, departureDate);
        
        if (flights.isEmpty()) {
            throw new FlightNotFoundException();
        }
        
        List<FlightDto> flightDtos = flights.stream()
                .map(flightMapper::toFlightDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(flightDtos);
    }
}