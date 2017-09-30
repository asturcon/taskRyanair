package com.marcos.ryanair.interconnectingflights.service;

import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;

public interface RoutesService {

	public RoutesInfoDto getRoutesByDeparture();
	
	public RoutesInfoDto getRoutesByArrival();	
	
}
