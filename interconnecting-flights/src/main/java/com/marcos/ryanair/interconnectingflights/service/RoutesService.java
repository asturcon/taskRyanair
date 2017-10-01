package com.marcos.ryanair.interconnectingflights.service;

import java.util.List;
import java.util.Map;

import com.marcos.ryanair.interconnectingflights.model.Route;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

/**
 * Defines the service that works with routes.
 * 
 * @author mlg
 *
 */
public interface RoutesService {

	/**
	 * Find all the possible routes between 2 airports with the maximum stops
	 * given.
	 * 
	 * @param departure
	 * @param arrival
	 * @param maxStops
	 * @return
	 * @throws ServiceException
	 */
	public Map<Integer, List<Route>> findFlightsWithStops(String departure, String arrival, int maxStops)
			throws ServiceException;

}
