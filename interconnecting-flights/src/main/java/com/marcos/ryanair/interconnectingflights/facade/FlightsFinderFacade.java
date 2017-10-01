package com.marcos.ryanair.interconnectingflights.facade;

import java.time.Duration;
import java.time.LocalDateTime;

import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

/**
 * Facade for the flights finder.
 * 
 * @author mlg
 *
 */
public interface FlightsFinderFacade {

	/**
	 * Find all the flights between the departure and arrival airports that
	 * departure not earlier than the departureTime and arrive not later than
	 * the arrival time for a maximum number of stops and a minimum time for the
	 * stop duration.
	 * 
	 * @param departure
	 *            departure airport
	 * @param arrival
	 *            arrival airport
	 * @param departureDateTime
	 *            earliest departure time from the departure airport.
	 * @param arrivalDateTime
	 *            latest arrival time to the arrival airport.
	 * @param maxStops
	 *            max stops allowed for the flights.
	 * @param minTimeStop
	 *            minimum time for the stops.
	 * @return all the flights that match the conditions given.
	 * @throws ServiceException
	 *             if it was not possible to find the flighs.
	 */
	public FlightsInfo findFlights(String departure, String arrival, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, int maxStops, Duration minTimeStop) throws ServiceException;

}
