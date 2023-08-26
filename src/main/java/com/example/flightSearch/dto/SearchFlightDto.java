package com.example.flightSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchFlightDto {
    private int departureAirportId;
    private int arrivalAirportId;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
}
