package com.asif.flightreservation.services;

import com.asif.flightreservation.dto.ReservationRequest;
import com.asif.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
