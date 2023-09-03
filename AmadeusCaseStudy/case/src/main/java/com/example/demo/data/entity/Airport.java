package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departingFlights;

    @JsonIgnore
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivingFlights;
}
