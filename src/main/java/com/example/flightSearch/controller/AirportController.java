package com.example.flightSearch.controller;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Airport>> getAllAirports(){
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable(name = "id") int id) throws Exception {
        Airport airport = airportService.getAirportById(id);
        return ResponseEntity.ok(airport);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteAirport(@PathVariable(name = "id") int id) throws Exception {
        return airportService.deleteAirport(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport) throws Exception {
        Airport updatedAirport= airportService.updateAirport(airport);
        return ResponseEntity.ok(updatedAirport);
    }
    @PostMapping("/add")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport){
        Airport addedAirport= airportService.addAirport(airport);
        return ResponseEntity.ok(addedAirport);
    }
}
