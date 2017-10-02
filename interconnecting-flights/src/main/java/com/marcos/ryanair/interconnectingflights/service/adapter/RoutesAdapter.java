package com.marcos.ryanair.interconnectingflights.service.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.service.dto.RoutesInfoDto;

/**
 * Routes adapter.
 * 
 * @author mlg
 *
 */
public interface RoutesAdapter {

	/**
	 * Adapts JSON-format routes grouping by departure.
	 * 
	 * @param json
	 *            json with the routes info
	 * @return {@link RoutesInfoDto} with the routes adapted. Only the map
	 *         {@link RoutesInfoDto#getRoutesByDepartureMap()} is filled.
	 */
	public RoutesInfoDto adaptRoutesByDeparture(JsonNode json);

	/**
	 * Adapts JSON-format routes grouping by arrival.
	 * 
	 * @param json
	 *            json with the routes info
	 * @return {@link RoutesInfoDto} with the routes adapted. Only the map
	 *         {@link RoutesInfoDto#getRoutesByArrivalMap()} is filled.
	 */
	public RoutesInfoDto adaptRoutesByArrival(JsonNode json);

}
