package com.marcos.ryanair.interconnectingflights.facade;

import java.util.Date;

public interface FlightsFinderFacade {

	public void findFlights(String departure, String arrival, Date departureDateTime, Date arrivalDateTime);
	
}
