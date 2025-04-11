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
    @Column(nullable = false)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "outbound_flight_id")
    private Flight outboundFlight;

    private boolean checkedIn;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
    private String bookingReference;


}
