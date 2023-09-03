package com.example.demo.presentation.components;

import com.example.demo.data.entity.Flight;
import com.example.demo.data.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightDataUpdater {
    @Autowired
    FlightRepository flightRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 0 0 * * ?") // Midnight everyday.
    public void updateFlightData() {
        String mockApiResponse = restTemplate.getForObject("/app/third-party/flight-data", String.class);
        Flight flight = convertJsonToFlight(mockApiResponse);
        flightRepository.save(flight);
    }

    private Flight convertJsonToFlight(String mockApiResponse) {
        return null;
    }


}
