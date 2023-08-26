package com.example.flightSearch.mock;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockFlightApi {
    public static List<Flight> getMockFlights() {
        List<Flight> flights = new ArrayList<>();
        Airport airport1 = new Airport(1, "London");
        Airport airport2 = new Airport(2, "Paris");
        LocalDateTime departureDate = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime returnDate = LocalDateTime.of(2023, 8, 28, 18, 30);

        Flight flight1 = new Flight(20, airport1, airport2, departureDate, returnDate, 350.0);
        Flight flight2 = new Flight(21, airport2, airport1, departureDate, returnDate, 460.0);
        flights.add(flight1);
        flights.add(flight2);
        return flights;
    }
}
