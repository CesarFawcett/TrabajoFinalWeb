package edu.unimagdalena.entities;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Representa una reserva de vuelo en el sistema.
 * <p>
 * Mapeada a la tabla "bookings" en la base de datos.
 * 
 * @since 1.0
 * @version 1.1
 */

@Entity
@Table(name = "bookings") 
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Booking", description = "Entidad que representa una reserva de vuelo")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la reserva", example = "1")
    private int id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Estado de la reserva", example = "CONFIRMED")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "outbound_flight_id")
    @Schema(description = "Vuelo de ida asociado")
    private Flight outboundFlight;

    @Schema(description = "Indica si el pasajero hizo check-in", example = "false")
    private boolean checkedIn;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    @Schema(description = "Usuario que realizó la reserva")
    private User user;

    @Schema(description = "Código de referencia único de la reserva", example = "AB_123")
    private String bookingReference;


}
