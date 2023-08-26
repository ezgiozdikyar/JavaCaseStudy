package com.example.flightSearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @OneToOne()
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @OneToOne()
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;
    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;
    @Column(name = "price", nullable = false)
    private double price;
}
