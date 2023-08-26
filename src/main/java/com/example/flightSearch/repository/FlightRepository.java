package com.example.flightSearch.repository;

import com.example.flightSearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FlightRepository extends JpaRepository<Flight,Integer> {

}
