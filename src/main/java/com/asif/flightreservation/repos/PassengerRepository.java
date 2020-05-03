package com.asif.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asif.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
