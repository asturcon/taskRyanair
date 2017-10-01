package com.marcos.ryanair.interconnectingflights.facade.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.model.Route;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;
import com.marcos.ryanair.interconnectingflights.service.SchedulesService;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

@Component("flightsFinderFacade")
public class FlightsFinderFacadeImpl implements FlightsFinderFacade {

	@Autowired
	private RoutesService routesService;

	@Autowired
	private SchedulesService schedulesService;

	@Override
	public FlightsInfo findFlights(String departure, String arrival, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, int maxStops, Duration minTimeStop) throws ServiceException {

		FlightsInfo flightsInfo = new FlightsInfo();

		// get available routes for the maxStops given.
		Map<Integer, List<Route>> routesMap = routesService.findFlightsWithStops(departure, arrival, maxStops);

		Map<Integer, List<Flight>> interconnectingFlights = new HashMap<>();
		flightsInfo.setInterconnectingFlights(interconnectingFlights);

		// iterate by stops
		for (int stops = 0; stops <= maxStops; stops++) {
			List<Flight> flights = new ArrayList<>();
			interconnectingFlights.put(stops, flights);

			// get routes for this number of stops
			List<Route> routes = routesMap.get(stops);

			// iterate over each route
			for (Route route : routes) {
				// check schedules for this route
				flights.addAll(schedulesService.findRouteFlightsMax1Stop(route, departureDateTime, arrivalDateTime,
						minTimeStop));
			}

		}

		return flightsInfo;
	}

}
