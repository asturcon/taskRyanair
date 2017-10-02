package com.marcos.ryanair.interconnectingflights.provider.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.provider.SchedulesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.service.dto.SchedulesDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class SchedulesDataProviderRyanairTest {

	@Autowired
	private SchedulesDataProvider schedulesDataProvider;

	@Test
	@Ignore
	public void schedulesTest() {

		// TODO: delete hardcoded
		String departure = "DUB";
		String arrival = "WRO";
		int year = 2017;
		int month = 10;

		SchedulesDto schedulesInfo = null;
		try {
			schedulesInfo = schedulesDataProvider.getFlightsSchedule(departure, arrival, year, month);
		} catch (DataProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(true, !schedulesInfo.getFlights().isEmpty());
	}

}
