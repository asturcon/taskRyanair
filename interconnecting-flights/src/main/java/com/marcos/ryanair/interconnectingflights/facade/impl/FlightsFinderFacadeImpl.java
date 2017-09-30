package com.marcos.ryanair.interconnectingflights.facade.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.helpers.RoutesHelper;
import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.model.dto.RouteDto;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;
import com.marcos.ryanair.interconnectingflights.service.SchedulesService;

public class FlightsFinderFacadeImpl implements FlightsFinderFacade {

	@Autowired
	private RoutesService routesService;

	@Autowired
	private SchedulesService schedulesService;

	@Override
	public FlightsInfo findFlights(String departure, String arrival, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, int maxStops) {

		FlightsInfo flightsInfo = new FlightsInfo();

		// get available routes for the maxStops given.
		Map<Integer, List<RouteDto>> routesMap = RoutesHelper.findFlightsWithStops(routesService.getRoutesByDeparture(),
				departure, arrival, maxStops);

		Map<Integer, List<Flight>> interconnectingFlights = new HashMap<>();
		// iterate by stops
		for (int stops = 0; stops <= maxStops; stops++) {
			List<Flight> flights = new ArrayList<>();
			interconnectingFlights.put(stops, flights);

			// get routes for this number of stops
			List<RouteDto> routes = routesMap.get(stops);

			// iterate over each route
			for (RouteDto routeDto : routes) {
				// check schedules for this route
				List<String> routeAirports = routeDto.getAirports();
				for (int i = 0; i < routeAirports.size() - 1; i++) {
//					schedulesService.getFlightsSchedule(routeAirports.get(i), routeAirports.get(i+1), year, month)
				}
			}

		}

		return flightsInfo;
	}

}
