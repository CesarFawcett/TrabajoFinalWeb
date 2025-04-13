package edu.unimagdalena.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa un vuelo en el sistema de reservas.
 * <p>
 * Mapeada a la tabla "flights" en la base de datos.
 */

@Entity
@Table(name ="flights")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Flight", description = "Entidad que representa un vuelo programado")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del vuelo", example = "101")
    private int id;

    @Column(nullable = false)
    @Schema(description = "Fecha de salida (YYYY-MM-DD)", example = "2023-12-15")
    private String departureDate;
    
    @Column(nullable = false)
    @JoinColumn(name = "departureAirportCode")
    @Schema(description = "Código del aeropuerto de salida", example = "BOG")
    private String departureAirportCode;

    @Schema(description = "Nombre del aeropuerto de salida", example = "El Dorado")
    private String departureAirportName;

    @Schema(description = "Ciudad de salida", example = "City 1")
    private String departureCity;

    @Schema(description = "Región de salida", example = "Locale 1")
    private String departureLocale;

    @Schema(description = "Fecha de llegada", example = "2022-04-29T11:30:00")
    @Column(nullable = false)
    private LocalDateTime arrivalDate;


    @Column(nullable = false)
    @Schema(description = "Código del aeropuerto de llegada", example = "BOG")
    private String arrivalAirportCode;

    @Schema(description = "Nombre del aeropuerto de llegad", example = "Airport 2")
    private String arrivalAirportName;

    @Schema(description = "Ciudad de llegada", example = "City 2")
    private String arrivalCity;

    @Schema(description = "Región de llegada", example = "Locale 2")
    private String arrivalLocale;

    @Schema(description = "Precio del billete", example = "100")
    private int ticketPrice;

    @Schema(description = "Moneda del valor", example = "USD")
    private String ticketCurrency;

    @Column(nullable = false)
    @Schema(description = "Número de vuelo", example = "123")
    private int flightNumber;

    @Schema(description = "Capacidad de asientos", example = "600")
    private int seatCapacity; 

    @OneToMany(mappedBy = "outboundFlight")
    @Schema(description = "Lista de reservas asociadas a este vuelo (lado inverso de la relación)")
    private List<Booking> outboundFlight = new ArrayList<>();

}
