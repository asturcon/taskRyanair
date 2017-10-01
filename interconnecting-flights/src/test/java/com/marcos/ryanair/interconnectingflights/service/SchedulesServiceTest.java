package com.marcos.ryanair.interconnectingflights.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.model.Leg;
import com.marcos.ryanair.interconnectingflights.model.Route;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class SchedulesServiceTest {

	@Autowired
	private SchedulesService schedulesService;

	@Test
	public void findDirectFlightsTest() {

		Route route = new Route();
		List<String> airports = new ArrayList<>();
		airports.add("DUB");
		airports.add("MAD");
		route.setAirports(airports);

		LocalDateTime departureDateTime = LocalDateTime.of(2017, 10, 10, 8, 0);
		LocalDateTime arrivalDateTime = LocalDateTime.of(2017, 10, 10, 23, 0);

		List<Leg> flights = schedulesService.findRouteFlightsMax1Stop(route, departureDateTime, arrivalDateTime,
				Duration.ofHours(2));

		System.out.println(flights);

		for(Leg flight : flights) {
			Assert.assertEquals(false, flight.getDepartureTime().isBefore(departureDateTime));
			Assert.assertEquals(false, flight.getArrivalTime().isAfter(arrivalDateTime));
		}		

	}
	
	@Test
	public void findFlightsMax1StopNoCombinationsTest() {

		Route route = new Route();
		List<String> airports = new ArrayList<>();
		airports.add("DUB");
		airports.add("MAD");
		airports.add("WRO");
		route.setAirports(airports);

		LocalDateTime departureDateTime = LocalDateTime.of(2017, 10, 10, 8, 0);
		LocalDateTime arrivalDateTime = LocalDateTime.of(2017, 10, 10, 23, 0);

		List<Leg> flights = schedulesService.findRouteFlightsMax1Stop(route, departureDateTime, arrivalDateTime,
				Duration.ofHours(2));

		System.out.println(flights);

		Assert.assertEquals(true, flights.isEmpty());

	}
	
	@Test
	public void findFlightsMax1StopOneCombinationFoundTest() {

		Route route = new Route();
		List<String> airports = new ArrayList<>();
		airports.add("DUB");
		airports.add("MAD");
		airports.add("WRO");
		route.setAirports(airports);

		LocalDateTime departureDateTime = LocalDateTime.of(2017, 10, 10, 8, 0);
		LocalDateTime arrivalDateTime = LocalDateTime.of(2017, 10, 10, 23, 55);

		List<Leg> flights = schedulesService.findRouteFlightsMax1Stop(route, departureDateTime, arrivalDateTime,
				Duration.ofHours(2));

		System.out.println(flights);

		Assert.assertEquals(true, flights.size() == 2);

	}

}
