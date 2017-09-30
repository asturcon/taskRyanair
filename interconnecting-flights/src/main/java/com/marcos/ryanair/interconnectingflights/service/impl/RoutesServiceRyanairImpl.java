package com.marcos.ryanair.interconnectingflights.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.adapter.RoutesAdapter;
import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;
import com.marcos.ryanair.interconnectingflights.service.RestConnectorManager;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;

@Service("routesService")
public class RoutesServiceRyanairImpl implements RoutesService {

	@Autowired
	private RestConnectorManager connectorManager;
	
	@Autowired
	private RoutesAdapter routesAdapter;

	@Override
	public RoutesInfoDto getRoutesByDeparture() {

		// TODO: sacar url a properties
		JsonNode jsonNode = connectorManager.getRestResponseAsJson("https://api.ryanair.com/core/3/routes/");			

		return routesAdapter.adaptRoutesByDeparture(jsonNode);
	}

	@Override
	public RoutesInfoDto getRoutesByArrival() {
		
		JsonNode jsonNode = connectorManager.getRestResponseAsJson("https://api.ryanair.com/core/3/routes/");
		
		return routesAdapter.adaptRoutesByArrival(jsonNode);
	}

}
