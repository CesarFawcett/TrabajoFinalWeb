package edu.unimagdalena.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="flights")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String departureDate;
    
    @Column(nullable = false)
    @JoinColumn(name = "departureAirportCode")
    private String departureAirportCode;

    private String departureAirportName;
    private String departureCity;
    private String departureLocale;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @Column(nullable = false)
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalCity;
    private String arrivalLocale;
    private int ticketPrice;
    private String ticketCurrency;

    @Column(nullable = false)
    private int flightNumber;
    private int seatCapacity; 

    @OneToMany(mappedBy = "outboundFlight")
    private List<Booking> outboundFlight = new ArrayList<>();

}
