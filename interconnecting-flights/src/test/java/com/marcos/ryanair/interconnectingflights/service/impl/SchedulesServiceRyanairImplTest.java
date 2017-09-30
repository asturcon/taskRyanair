package com.marcos.ryanair.interconnectingflights.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesInfoDto;
import com.marcos.ryanair.interconnectingflights.service.SchedulesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class SchedulesServiceRyanairImplTest {

	@Autowired
	private SchedulesService schedulesService;

	@Test
	public void schedulesTest() {

		// TODO: delete hardcoded
		String departure = "DUB";
		String arrival = "WRO";
		int year = 2017;
		int month = 10;

		SchedulesInfoDto schedulesInfo = schedulesService.getFlightsSchedule(departure, arrival, year, month);

		Assert.assertEquals(true, !schedulesInfo.getSchedules().isEmpty());
		Assert.assertEquals(true, schedulesInfo.getSchedulesByMonth(month) != null);
	}

}
