package com.marcos.ryanair.interconnectingflights.service.adapter.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.service.adapter.RoutesAdapter;
import com.marcos.ryanair.interconnectingflights.service.dto.RoutesInfoDto;

@Component("routesAdapter")
public class RoutesAdapterImpl implements RoutesAdapter {

	private static final String AIRPORT_FROM_ATTR = "airportFrom";
	private static final String AIRPORT_TO_ATTR = "airportTo";

	@Override
	public RoutesInfoDto adaptRoutesByDeparture(JsonNode json) {

		Map<String, Set<String>> destinationsByOriginMap = new HashMap<>();

		for (JsonNode element : json) {
			String origin = element.get(AIRPORT_FROM_ATTR).asText();
			String destination = element.get(AIRPORT_TO_ATTR).asText();

			// add to destinations by origin map
			Set<String> destinations = destinationsByOriginMap.get(origin);
			if (destinations == null) {
				destinations = new HashSet<>();
				destinationsByOriginMap.put(origin, destinations);
			}
			destinations.add(destination);

		}

		RoutesInfoDto routesInfo = new RoutesInfoDto();
		routesInfo.setRoutesByDepartureMap(destinationsByOriginMap);

		return routesInfo;
	}

	@Override
	public RoutesInfoDto adaptRoutesByArrival(JsonNode json) {

		Map<String, Set<String>> originsByDestinationMap = new HashMap<>();

		for (JsonNode element : json) {
			String origin = element.get(AIRPORT_FROM_ATTR).asText();
			String destination = element.get(AIRPORT_TO_ATTR).asText();

			// add to destinations by origin map
			Set<String> origins = originsByDestinationMap.get(destination);
			if (origins == null) {
				origins = new HashSet<>();
				originsByDestinationMap.put(destination, origins);
			}
			origins.add(origin);

		}

		RoutesInfoDto routesInfo = new RoutesInfoDto();
		routesInfo.setRoutesByArrivalMap(originsByDestinationMap);

		return routesInfo;
	}

}
