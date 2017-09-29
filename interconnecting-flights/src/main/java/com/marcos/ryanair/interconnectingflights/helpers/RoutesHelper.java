package com.marcos.ryanair.interconnectingflights.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.marcos.ryanair.interconnectingflights.model.RoutesInfo;

public final class RoutesHelper {

	public final static Map<Integer, List<List<String>>> findFlightsWithStops(RoutesInfo routesInfo, String departure,
			String arrival, int maxStops) {

		int stops = 0;
		/**
		 * Key -> number of stops. Value -> routes
		 */
		Map<Integer, List<List<String>>> allRoutes = new HashMap<>();

		findFlightsWithStopsRecursive(routesInfo, departure, arrival, stops, maxStops, new ArrayList<>(), allRoutes);

		return allRoutes;
	}

	private static void findFlightsWithStopsRecursive(RoutesInfo routesInfo, String departure, String arrival,
			int stops, int maxStops, List<String> route, Map<Integer, List<List<String>>> allRoutes) {

		// get destinations of the current departure airport.
		Set<String> destinations = routesInfo.getRoutesByDepartureMap().get(departure);

		if (stops >= maxStops) {
			// we reach the maximum of stops. Add only the routes that match
			// with the arrival airport.
			for (String dest : destinations) {
				if (dest.equals(arrival)) {
					route.add(dest);
					addRoute(route, allRoutes, stops);
					break;
				}
			}

			return;
		} else {

			for (String dest : destinations) {
				if (dest.equals(arrival)) {
					// we found the arrival airport. We add the route and we do
					// not continue checking more stops.
					addRoute(createRoute(route, dest), allRoutes, stops);
				} else {
					// arrival not found, we keep checking more stops.
					findFlightsWithStopsRecursive(routesInfo, dest, arrival, stops + 1, maxStops,
							createRoute(route, dest), allRoutes);
				}

			}
		}

	}

	/**
	 * Adds a route to the routes by stops map.
	 */
	private static void addRoute(List<String> route, Map<Integer, List<List<String>>> allRoutes, int stops) {
		List<List<String>> routesStop = allRoutes.get(stops);
		if (routesStop == null) {
			routesStop = new ArrayList<>();
			allRoutes.put(stops, routesStop);
		}

		routesStop.add(route);
	}

	/**
	 * Creates a new route adding a new stop to the current route.
	 */
	private static List<String> createRoute(List<String> currentRoute, String newStop) {
		List<String> newRoute = new ArrayList<>(currentRoute);		
		newRoute.add(newStop);

		return newRoute;
	}

}
