package com.example.flightSearch.service;

import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public boolean deleteAirport(int id) throws Exception {
        Airport airport = airportRepository.findById(id).orElse(null);
        if(airport == null)
            throw new Exception("This airport does not exist!");
        airportRepository.delete(airport);
        return true;
    }

    public Airport addAirport(Airport newAirport){
        return airportRepository.save(newAirport);
    }

    public Airport updateAirport(Airport newAirport) throws Exception {
        Airport airport = airportRepository.findById(newAirport.getId()).orElse(null);
        if(airport == null){
            throw new Exception("This airport does not exist!");
        }
        airport.setCity(newAirport.getCity());
        return airportRepository.save(airport);
    }

    public Airport getAirportById(int id) throws Exception {
        Airport airport = airportRepository.findById(id).orElse(null);
        if(airport != null)
            return airport;
        throw new Exception("This airport does not exist.");
    }

    public List<Airport> getAllAirports(){
        List<Airport> airports = airportRepository.findAll();
        return airports;
    }
}
