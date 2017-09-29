package com.marcos.ryanair.interconnectingflights.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.model.RoutesInfo;

public interface RoutesAdapter {

	RoutesInfo adaptRoutesByDeparture(JsonNode json);
	
	RoutesInfo adaptRoutesByArrival(JsonNode json);
	
}
