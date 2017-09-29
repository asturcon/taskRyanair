package com.marcos.ryanair.interconnectingflights.facade.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.model.RoutesInfo;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;

public class FlightsFinderFacadeImpl implements FlightsFinderFacade{

	@Autowired
	private RoutesService routesService;
	
	@Override
	public void findFlights(String departure, String arrival, Date departureDateTime, Date arrivalDateTime) {

		
		
		
		
	}

}
