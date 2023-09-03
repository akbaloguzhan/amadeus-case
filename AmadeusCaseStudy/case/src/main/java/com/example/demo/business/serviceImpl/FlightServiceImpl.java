package com.example.demo.business.serviceImpl;

import com.example.demo.business.service.AirportService;
import com.example.demo.business.service.FlightService;
import com.example.demo.data.entity.Airport;
import com.example.demo.data.entity.Flight;
import com.example.demo.data.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportService airportService;

    @Override
    public String addFlight(Flight flight) {
        try {
            flightRepository.save(flight);

            return "Flight added successfully with id: " + flight.getId();
        } catch (Exception e) {
            return "An error occurred while adding new flight: " + e.getMessage();
        }
    }

    @Override
    public Flight getFlightById(Long id) {
        try {
            return flightRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to get flight by id.", e);
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        try {
            return flightRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to get all flights. ", e);
        }
    }

    @Override
    public Flight updateFlight(Long id, Flight updatedFlight) {
        try {
            Flight existingFlight = flightRepository.findById(id).orElse(null);

            if (existingFlight != null)  {
                existingFlight.setPrice(updatedFlight.getPrice());
                existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
                existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
                existingFlight.setReturnDate(updatedFlight.getReturnDate());
                existingFlight.setDepartureDate(updatedFlight.getDepartureDate());

                return flightRepository.save(existingFlight);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the flight. ", e);
        }
    }

    @Override
    public String deleteFlight(Long id) {
        try {
            flightRepository.deleteById(id);

            return "Flight deleted successfully!";
        } catch (Exception e) {
            return "An error occurred while deleting the saved flight: " + e.getMessage();
        }
    }

    @Override
    public List<Flight> findFlights(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate) {
        try {
            Airport departureAirport = airportService.getAirportByCity(departureCity);
            Airport arrivalAirport = airportService.getAirportByCity(arrivalCity);

            if (returnDate == null) {
                return flightRepository.findFlightsForSearch(departureAirport.getId(), arrivalAirport.getId(), departureDate);
            } else {
                List<Flight> departingFlights =
                        flightRepository.findFlightsForSearch(departureAirport.getId(), arrivalAirport.getId(), departureDate);
                List<Flight> returningFlights =
                        flightRepository.findFlightsForSearch(arrivalAirport.getId(), departureAirport.getId(), returnDate);

                departingFlights.addAll(returningFlights);
                return departingFlights;
            }
        } catch (Exception e) {
            throw new RuntimeException("There is an error in service while finding specific flight", e);
        }
    }
}
