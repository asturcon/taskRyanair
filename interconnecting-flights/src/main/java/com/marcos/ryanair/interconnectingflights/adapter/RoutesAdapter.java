package com.marcos.ryanair.interconnectingflights.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;

public interface RoutesAdapter {

	RoutesInfoDto adaptRoutesByDeparture(JsonNode json);
	
	RoutesInfoDto adaptRoutesByArrival(JsonNode json);
	
}
