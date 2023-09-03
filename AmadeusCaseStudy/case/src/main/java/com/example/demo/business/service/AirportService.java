package com.example.demo.business.service;

import com.example.demo.data.entity.Airport;

import java.util.List;

public interface AirportService {

    String addAirport(Airport airport);

    Airport getAirportById(Long id);

    List<Airport> getAllAirports();

    Airport updateAirport(Long id, Airport updatedAirport);

    String deleteAirport(Long id);

    Airport getAirportByCity(String city);
}
