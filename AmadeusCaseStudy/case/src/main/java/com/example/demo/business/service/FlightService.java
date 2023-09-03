package com.example.demo.business.service;

import com.example.demo.data.entity.Airport;
import com.example.demo.data.entity.Flight;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    String addFlight(Flight flight);

    Flight getFlightById(Long id);

    List<Flight> getAllFlights();

    Flight updateFlight(Long id, Flight updatedFlight);

    String deleteFlight(Long id);

    List<Flight> findFlights(String departureCity, String arrivalCity, LocalDate departureDateTime, LocalDate returnDateTime);
}
