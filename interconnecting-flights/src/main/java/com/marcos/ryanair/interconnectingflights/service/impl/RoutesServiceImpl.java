package com.marcos.ryanair.interconnectingflights.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.ryanair.interconnectingflights.model.Route;
import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;
import com.marcos.ryanair.interconnectingflights.provider.RoutesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

@Service("routesService")
public final class RoutesServiceImpl implements RoutesService {

	@Autowired
	private RoutesDataProvider routesService;

	@Override
	public Map<Integer, List<Route>> findFlightsWithStops(String departure, String arrival, int maxStops)
			throws ServiceException {

		RoutesInfoDto routesInfo;
		try {
			routesInfo = routesService.getRoutesGroupedByDeparture();
		} catch (DataProviderException ex) {
			throw new ServiceException("Could not get routes", ex);
		}

		int stops = 0;
		/**
		 * Key -> number of stops. Value -> routes
		 */
		Map<Integer, List<Route>> allRoutes = new HashMap<>();

		findFlightsWithStopsRecursive(routesInfo, departure, arrival, stops, maxStops, new ArrayList<>(), allRoutes);

		return allRoutes;
	}

	private static void findFlightsWithStopsRecursive(RoutesInfoDto routesInfo, String departure, String arrival,
			int stops, int maxStops, List<String> route, Map<Integer, List<Route>> allRoutes) {

		route.add(departure);

		// get destinations of the current departure airport.
		Set<String> destinations = routesInfo.getRoutesByDepartureMap().get(departure);

		if (stops >= maxStops) {
			// we reach the maximum of stops. Add only the route if it has the
			// arrival airport as its last destination.
			if (destinations.contains(arrival)) {
				route.add(arrival);
				addRoute(route, allRoutes, stops);
			}
		} else {
			for (String dest : destinations) {
				if (dest.equals(arrival)) {
					// we found the arrival airport. We add the route and we do
					// not continue checking more stops.
					List<String> newRoute = new ArrayList<>(route);
					newRoute.add(arrival);
					addRoute(newRoute, allRoutes, stops);
				} else {
					// arrival not found, we keep checking more stops.
					findFlightsWithStopsRecursive(routesInfo, dest, arrival, stops + 1, maxStops,
							new ArrayList<>(route), allRoutes);
				}
			}
		}
	}

	/**
	 * Adds a route to the routes by stops map.
	 */
	private static void addRoute(List<String> route, Map<Integer, List<Route>> allRoutes, int stops) {
		List<Route> routesStop = allRoutes.get(stops);
		if (routesStop == null) {
			routesStop = new ArrayList<>();
			allRoutes.put(stops, routesStop);
		}

		Route routeDto = new Route();
		routeDto.setAirports(route);

		routesStop.add(routeDto);
	}

}
