package com.marcos.ryanair.interconnectingflights.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.model.Route;
import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;
import com.marcos.ryanair.interconnectingflights.provider.RoutesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class RoutesServiceTest {

	@Autowired
	private RoutesService routesService;

	@Autowired
	private RoutesDataProvider routesDataProvider;

	@Test
	public void routesHelperTest1Stop() {

		String departure = "DUB";
		String arrival = "WRO";
		int maxStops = 1;

		Map<Integer, List<Route>> routes = null;
		try {
			routes = routesService.findFlightsWithStops(departure, arrival, maxStops);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		System.out.println(routes);

		// calculate the possible values semi-manually to test it
		RoutesInfoDto routesInfoByDeparture;
		try {
			routesInfoByDeparture = routesDataProvider.getRoutesGroupedByDeparture();
			RoutesInfoDto routesInfoByArrival = routesDataProvider.getRoutesGroupedByArrival();
			
			Set<String> destinationsFromOrigin = routesInfoByDeparture.getRoutesByDepartureMap().get(departure);
			Set<String> originsToDestination = routesInfoByArrival.getRoutesByArrivalMap().get(arrival);

			boolean direct = destinationsFromOrigin.contains(arrival);
			destinationsFromOrigin.retainAll(originsToDestination);
			int possibleFlightsWithStops = destinationsFromOrigin.size();

			Assert.assertEquals(direct ? maxStops + 1 : maxStops, routes.size());
			Assert.assertEquals(possibleFlightsWithStops, routes.get(maxStops).size());

		} catch (DataProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Test
	public void routesHelperTestDirect() {

		String departure = "STN";
		String arrival = "WRO";
		int maxStops = 0;

		Map<Integer, List<Route>> routes = null;
		try {
			routes = routesService.findFlightsWithStops(departure, arrival, maxStops);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(routes);

		Assert.assertEquals(1, routes.size());
		Assert.assertEquals(1, routes.get(maxStops).size());
	}
}
