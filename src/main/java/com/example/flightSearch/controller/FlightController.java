package com.example.flightSearch.controller;


import com.example.flightSearch.dto.SearchFlightDto;
import com.example.flightSearch.dto.UpdateFlightDto;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable(name = "id") int id) throws Exception {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteFlight(@PathVariable(name = "id") int id) throws Exception {
        return flightService.deleteFlight(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Flight> updateFlight(@RequestBody UpdateFlightDto updateFlightDto) throws Exception {
        Flight updatedFlight= flightService.updateFlight(updateFlightDto);
        return ResponseEntity.ok(updatedFlight);
    }
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight addedFlight= flightService.addFlight(flight);
        return ResponseEntity.ok(addedFlight);
    }

    @PostMapping("/search-flight")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody SearchFlightDto searchFlightDto) throws Exception {
        List<Flight> flights = flightService.searchFlights(searchFlightDto);
        return ResponseEntity.ok(flights);
    }
}
