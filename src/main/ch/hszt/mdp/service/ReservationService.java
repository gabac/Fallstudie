package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.domain.Reservation;

public interface ReservationService {
	public List<Reservation> query(String courtName);
}
