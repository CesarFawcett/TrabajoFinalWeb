package edu.unimagdalena.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight outboundFlight;
    private boolean checkedIn;
    @ManyToOne(optional = true)
    @JoinColumn(name = "userId")
    private User customerName;
    private String createdAt;
    private String bookingReference;
}
