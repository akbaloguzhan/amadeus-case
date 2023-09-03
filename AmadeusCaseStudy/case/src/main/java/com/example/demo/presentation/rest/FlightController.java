package com.example.demo.presentation.rest;

import com.example.demo.business.service.AirportService;
import com.example.demo.business.service.FlightService;
import com.example.demo.data.entity.Airport;
import com.example.demo.data.entity.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Generated;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/amadeus")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @GetMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<List<Flight>> getAllFlights() {
        try {
            List<Flight> flights = flightService.getAllFlights();
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting all flights", e);
        }
    }

    @GetMapping(value = "/airports", produces = "application/json")
    public ResponseEntity<List<Airport>> getAllAirports() {
        try {
            List<Airport> airports = airportService.getAllAirports();
            return new ResponseEntity<>(airports, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting all airports", e);
        }
    }

    @PostMapping(value = "/flights-add", produces = "application/json")
    public String createFlight(@RequestBody Flight flight) {
        try {
            flightService.addFlight(flight);
            return "Flight created successfully";
        } catch (Exception e) {
            return "An error occurred in controller: " + e.getMessage();
        }
    }

    @PostMapping(value = "/airports-add", produces = "application/json")
    public String createAirport(@RequestBody Airport airport) {
        try {
            airportService.addAirport(airport);
            return "Airport created successfully";
        } catch (Exception e) {
            return "An error occurred in controller: " + e.getMessage();
        }
    }

    @DeleteMapping(value = "/flights-delete/{id}", produces = "application/json")
    public String deleteFlight(@PathVariable("id") Long id) {
        try {
            flightService.deleteFlight(id);
            return "Flight deleted successfully";
        } catch (Exception e) {
            return "An error occurred in controller flight delete: " + e.getMessage();
        }
    }
    @DeleteMapping(value = "/airports-delete/{id}", produces = "application/json")
    public String deleteAirport(@PathVariable("id") Long id) {
        try {
            airportService.deleteAirport(id);
            return "Airport deleted successfully";
        } catch (Exception e) {
            return "An error occurred in controller airport delete: " + e.getMessage();
        }
    }

    @PutMapping(value = "/flights-update/{id}", produces = "application/json")
    public String updateFlight(@PathVariable("id") Long id, @RequestBody Flight flight) {
        try {
            flightService.updateFlight(id, flight);
            return "Flight updated successfully: " + flight.toString();
        } catch (Exception e) {
            return "An error occurred in controller flight update: " + e.getMessage();
        }
    }

    @PutMapping(value = "/airports-update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateAirport(@PathVariable("id") Long id, @RequestBody Airport airport) {
        try {
            airportService.updateAirport(id, airport);
            return "Airport updated successfully: " + airport.toString();
        } catch (Exception e) {
            return "An error occurred in controller airport update: " + e.getMessage();
        }
    }

    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonFormat(pattern = "application/json")
    public List<Flight> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam(name = "departureTime") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDateTime,
            @RequestParam(name = "returnTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDateTime
            ) {
        try {
            return flightService.findFlights(departureCity, arrivalCity, departureDateTime, returnDateTime);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while searching specific flight!", e);
        }
    }

    @GetMapping("/airports/by-city/{city}")
    public Airport searchAirportByCity(@PathVariable("city") String city) {
        return airportService.getAirportByCity(city);
    }
}
