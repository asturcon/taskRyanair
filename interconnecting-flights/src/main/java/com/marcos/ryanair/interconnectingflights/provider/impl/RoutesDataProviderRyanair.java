package com.marcos.ryanair.interconnectingflights.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.provider.RestConnectorManager;
import com.marcos.ryanair.interconnectingflights.provider.RoutesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.provider.exception.RestConnectionException;
import com.marcos.ryanair.interconnectingflights.service.adapter.RoutesAdapter;
import com.marcos.ryanair.interconnectingflights.service.dto.RoutesInfoDto;

@Service("routesDataProvider")
public class RoutesDataProviderRyanair implements RoutesDataProvider {

	@Autowired
	private RestConnectorManager connectorManager;

	@Autowired
	private RoutesAdapter routesAdapter;

	@Override
	public RoutesInfoDto getRoutesGroupedByDeparture() throws DataProviderException {

		// TODO: sacar url a properties
		JsonNode jsonNode;
		try {
			jsonNode = connectorManager.getRestResponseAsJson("https://api.ryanair.com/core/3/routes/");
		} catch (RestConnectionException ex) {
			throw new DataProviderException("Could not fetch the routes from the provider", ex);
		}

		return routesAdapter.adaptRoutesByDeparture(jsonNode);
	}

	@Override
	public RoutesInfoDto getRoutesGroupedByArrival() throws DataProviderException {

		JsonNode jsonNode;
		try {
			jsonNode = connectorManager.getRestResponseAsJson("https://api.ryanair.com/core/3/routes/");
		} catch (RestConnectionException ex) {
			throw new DataProviderException("Could not fetch the routes from the provider", ex);
		}

		return routesAdapter.adaptRoutesByArrival(jsonNode);
	}

}
