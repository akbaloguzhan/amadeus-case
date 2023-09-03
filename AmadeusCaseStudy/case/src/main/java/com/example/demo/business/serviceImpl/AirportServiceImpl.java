package com.example.demo.business.serviceImpl;

import com.example.demo.business.service.AirportService;
import com.example.demo.data.entity.Airport;
import com.example.demo.data.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public String addAirport(Airport airport) {
        try {
            airportRepository.save(airport);

            return airport.getCity() + " City Airport has added successfully!";
        } catch (Exception e) {
            return "An error occurred while adding Airport: " + e.getMessage();
        }
    }

    @Override
    public Airport getAirportById(Long id) {
        try {
            return airportRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to get airport by id.", e);
        }
    }

    @Override
    public List<Airport> getAllAirports() {
        try {
            return airportRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to get all airports. ", e);
        }
    }

    @Override
    public Airport updateAirport(Long id, Airport updatedAirport) {
        try {
            Airport existingAirport = airportRepository.findById(id).orElse(null);

            if (existingAirport != null)  {
                existingAirport.setCity(updatedAirport.getCity());
                return airportRepository.save(existingAirport);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the airport. ", e);
        }
    }

    @Override
    public String deleteAirport(Long id) {
        try {
            airportRepository.deleteById(id);

            return "Airport deleted successfully!";
        } catch (Exception e) {
            return "An error occurred while deleting the saved airport: " + e.getMessage();
        }
    }

    @Override
    public Airport getAirportByCity(String city) {
        return airportRepository.findAirportByCity(city);
    }
}
