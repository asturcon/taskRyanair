package com.marcos.ryanair.interconnectingflights.facade;

import java.time.LocalDateTime;

import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;

public interface FlightsFinderFacade {

	public FlightsInfo findFlights(String departure, String arrival, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, int maxStops);

}
