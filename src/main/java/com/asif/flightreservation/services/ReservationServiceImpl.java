package com.asif.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asif.flightreservation.dto.ReservationRequest;
import com.asif.flightreservation.entities.Flight;
import com.asif.flightreservation.entities.Passenger;
import com.asif.flightreservation.entities.Reservation;
import com.asif.flightreservation.repos.FlightRepository;
import com.asif.flightreservation.repos.PassengerRepository;
import com.asif.flightreservation.repos.ReservationRepository;
import com.asif.flightreservation.util.EmailUtil;
import com.asif.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.asif.flightreservation.itinerary.dirpath}")
	private String ITINERY_DIR;

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {

		// make payment
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERY_DIR+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinery(savedReservation, filePath);
		
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
