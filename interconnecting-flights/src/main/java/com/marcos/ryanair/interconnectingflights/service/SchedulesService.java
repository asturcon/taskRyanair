package com.marcos.ryanair.interconnectingflights.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.Route;

public interface SchedulesService {

	// TODO: make it for any number of stops
	
	public List<Flight> findRouteFlightsMax1Stop(Route routeDto, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, Duration minTimeStop);

}
