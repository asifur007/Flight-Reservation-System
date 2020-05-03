package com.asif.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asif.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
