package com.marcos.ryanair.interconnectingflights.provider.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesDto;
import com.marcos.ryanair.interconnectingflights.provider.SchedulesDataProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class SchedulesDataProviderRyanairTest {

	@Autowired
	private SchedulesDataProvider schedulesDataProvider;

	@Test
	public void schedulesTest() {

		// TODO: delete hardcoded
		String departure = "DUB";
		String arrival = "WRO";
		int year = 2017;
		int month = 10;

		SchedulesDto schedulesInfo = schedulesDataProvider.getFlightsSchedule(departure, arrival, year, month);

		Assert.assertEquals(true, !schedulesInfo.getFlights().isEmpty());
	}

}
