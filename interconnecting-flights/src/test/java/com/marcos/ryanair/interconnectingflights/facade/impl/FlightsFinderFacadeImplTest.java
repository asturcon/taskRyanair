package com.marcos.ryanair.interconnectingflights.facade.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.model.Leg;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class FlightsFinderFacadeImplTest {

	@Autowired
	private FlightsFinderFacade flightsFinderFacade;

	@Test
	public void findFlightsTest() {

		String departure = "DUB";
		String arrival = "WRO";

		LocalDateTime departureDateTime = LocalDateTime.of(2017, 10, 1, 7, 0);
		LocalDateTime arrivalDateTime = LocalDateTime.of(2017, 10, 3, 21, 0);

		FlightsInfo flightsInfo = null;
		try {
			flightsInfo = flightsFinderFacade.findFlights(departure, arrival, departureDateTime, arrivalDateTime, 1,
					Duration.ofHours(2));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		// direct
		for (Flight flight : flightsInfo.getInterconnectingFlights().get(0)) {
			for (Leg leg : flight.getLegs()) {
				Assert.assertEquals(false, leg.getDepartureTime().isBefore(departureDateTime));
				Assert.assertEquals(false, leg.getArrivalTime().isAfter(arrivalDateTime));
			}
		}
		
		
		// 1 stop
		for (Flight flight : flightsInfo.getInterconnectingFlights().get(1)) {
			List<Leg> legs = flight.getLegs();
			for (Leg leg : legs) {
				Assert.assertEquals(false, leg.getDepartureTime().isBefore(departureDateTime));
				Assert.assertEquals(false, leg.getArrivalTime().isAfter(arrivalDateTime));
			}

			Assert.assertEquals(false,
					legs.get(1).getDepartureTime().isBefore(legs.get(0).getArrivalTime().plusHours(2)));

		}

	}
}
