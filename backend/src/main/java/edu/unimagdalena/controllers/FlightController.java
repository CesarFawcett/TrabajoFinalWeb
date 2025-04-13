package edu.unimagdalena.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Vuelo 1
        Flight flight1 = new Flight();
        flight1.setDepartureDate("2022-04-29");
        flight1.setDepartureAirportCode("LON");
        flight1.setDepartureAirportName("Airport 1");
        flight1.setDepartureCity("City 1");
        flight1.setDepartureLocale("Locale 1");
        flight1.setArrivalDate(LocalDateTime.parse("2022-04-29 11:30", dateFormatter));
        flight1.setArrivalAirportCode("ARG");
        flight1.setArrivalAirportName("Airport 2");
        flight1.setArrivalCity("City 2");
        flight1.setArrivalLocale("Locale 2");
        flight1.setTicketPrice(100);
        flight1.setTicketCurrency("USD");
        flight1.setFlightNumber(123);
        flight1.setSeatCapacity(200);
        flightService.createFlight(flight1);
    }

    @Operation(summary = "Obtener todos los vuelos", 
               description = "Retorna una lista completa de vuelos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de vuelos encontrados")
    @GetMapping("/")
    public ResponseEntity<List<FlightDto>> findAll() {
        List<Flight> flights = flightService.findAll();
        List<FlightDto> flightDtos = flights.stream()
                .map(flightMapper::toFlightDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flightDtos);
    }

    @Operation(summary = "Buscar vuelos con filtros", 
               description = "Permite filtrar vuelos por múltiples parámetros")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vuelos encontrados"),
        @ApiResponse(responseCode = "404", description = "No se encontraron vuelos")
    })
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

    @Operation(summary = "Buscar vuelos por aeropuerto y fecha")
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
    
    @Operation(summary = "Crear nuevo vuelo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Vuelo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "409", description = "Conflicto (ej: número de vuelo duplicado)")
    })
    @PostMapping("/")
    public ResponseEntity<FlightCreateDto>create(@RequestBody FlightCreateDto Flight){
        Flight newFlight = flightMapper.toEntity(Flight);
        Flight flightCreate = null;
        try{
            flightCreate = flightService.createFlight(newFlight);
        }catch (Exception e){
            throw new DuplicateCodigoException();
        }
        FlightCreateDto flightCreateDto = flightMapper.toFlightCreateDto(flightCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(flightCreateDto.getId())
                       .toUri();
            return ResponseEntity.created(location).body(flightCreateDto);
    }

    @Operation(summary = "Actualizar vuelo existente")
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

    @Operation(summary = "Eliminar vuelo")
    @ApiResponse(responseCode = "204", description = "Vuelo eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer id) {
        flightService.delete(id);

        return ResponseEntity.noContent().build();
    }

}