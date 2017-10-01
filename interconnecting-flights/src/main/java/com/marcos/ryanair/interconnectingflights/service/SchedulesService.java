package com.marcos.ryanair.interconnectingflights.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.Route;

/**
 * Defines the service that works with schedules.
 * 
 * @author mlg
 *
 */
public interface SchedulesService {

	// TODO: make it for any number of stops

	/**
	 * Finds the available flights for a route with maximum 1 stop.
	 * 
	 * @param routeDto
	 *            route
	 * @param departureDateTime
	 *            earliest departure time of the flights.
	 * @param arrivalDateTime
	 *            latest arrival time of the flights.
	 * @param minTimeStop
	 *            minimum amount of time for the stops.
	 * @return List of flights found.
	 */
	public List<Flight> findRouteFlightsMax1Stop(Route routeDto, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, Duration minTimeStop);

}
