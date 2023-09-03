package com.example.demo.data.repository;

import com.example.demo.data.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "SELECT * FROM airport a WHERE city = ?1 ", nativeQuery = true)
    Airport findAirportByCity(String city);
}
