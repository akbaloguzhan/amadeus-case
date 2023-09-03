package com.example.demo.data.repository;

import com.example.demo.data.entity.Airport;
import com.example.demo.data.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight f WHERE f.departure_airport_id = :departureAirport AND f.arrival_airport_id = :arrivalAirport AND f.departure_date = :inputDate", nativeQuery = true)
    List<Flight> findFlightsForSearch(@Param("departureAirport") Long departureAirport, @Param("arrivalAirport") Long arrivalAirport ,@Param("inputDate") LocalDate inputDate);
}
