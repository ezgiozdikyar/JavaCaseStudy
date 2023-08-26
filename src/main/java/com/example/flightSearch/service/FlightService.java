package com.example.flightSearch.service;

import com.example.flightSearch.dto.SearchFlightDto;
import com.example.flightSearch.dto.UpdateFlightDto;
import com.example.flightSearch.entity.Airport;
import com.example.flightSearch.entity.Flight;
import com.example.flightSearch.mock.MockFlightApi;
import com.example.flightSearch.repository.AirportRepository;
import com.example.flightSearch.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;

    public boolean deleteFlight(int id) throws Exception {
        Flight flight = flightRepository.findById(id).orElse(null);
        if(flight == null)
            throw  new Exception("This flight does not exist!");
        flightRepository.delete(flight);
        return true;
    }

    public Flight addFlight(Flight newFlight) {
        Flight flight = flightRepository.save(newFlight);
        Airport arrivalAirport = airportRepository.findById(flight.getArrivalAirport().getId()).orElse(null);
        flight.getArrivalAirport().setCity(arrivalAirport.getCity());

        Airport departureAirport = airportRepository.findById(flight.getDepartureAirport().getId()).orElse(null);
        flight.getDepartureAirport().setCity(departureAirport.getCity());
        return flight;
    }

    public Flight updateFlight(UpdateFlightDto updateFlightDto) throws Exception {
        Flight flight = flightRepository.findById(updateFlightDto.getId()).orElse(null);
        if(flight == null){
            throw new Exception("This flight does not exist!");
        }
        Airport departureAirport = airportRepository.findById(updateFlightDto.getDepartureAirportId()).orElse(null);
        flight.setDepartureAirport(departureAirport);
        Airport arrivalAirport = airportRepository.findById(updateFlightDto.getArrivalAirportId()).orElse(null);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureDate(updateFlightDto.getDepartureDate());
        flight.setReturnDate(updateFlightDto.getReturnDate());
        flight.setPrice(updateFlightDto.getPrice());
        return flightRepository.save(flight);
    }

    public Flight getFlightById(int id) throws Exception {
        Flight flight = flightRepository.findById(id).orElse(null);
        if(flight != null)
            return flight;
        throw new Exception("This flight does not exist.");
    }

    public List<Flight> getAllFlights(){
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }
    public List<Flight> searchFlights(SearchFlightDto searchFlightDto){
        List<Flight> allFlights = flightRepository.findAll();
        Set<Flight> filteredFlightsSet = allFlights.stream()
                .filter(flight -> flight.getDepartureAirport().getId() == searchFlightDto.getDepartureAirportId())
                .filter(flight -> flight.getArrivalAirport().getId() == searchFlightDto.getArrivalAirportId())
                .filter(flight -> flight.getDepartureDate().toLocalDate().isEqual(ChronoLocalDate.from(searchFlightDto.getDepartureDate())))
                .collect(Collectors.toSet());
        if(searchFlightDto.getReturnDate() != null){
            Set<Flight> filteredFlights2 = allFlights.stream()
                    .filter(flight -> flight.getArrivalAirport().getId() == searchFlightDto.getArrivalAirportId())
                    .filter(flight -> flight.getDepartureAirport().getId() == searchFlightDto.getDepartureAirportId())
                    .filter(flight -> flight.getReturnDate().toLocalDate().isEqual(ChronoLocalDate.from(searchFlightDto.getReturnDate())))
                    .collect(Collectors.toSet());
            filteredFlightsSet.addAll(filteredFlights2);
        }
        List<Flight> filteredFlights = new ArrayList<>(filteredFlightsSet);
        return filteredFlights;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateFlightData() {
        List<Flight> mockFlights = MockFlightApi.getMockFlights();
        flightRepository.saveAll(mockFlights);
    }
}
