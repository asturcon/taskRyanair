package com.marcos.ryanair.interconnectingflights.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;
import com.marcos.ryanair.interconnectingflights.service.RoutesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class RoutesServiceRyanairImplTest {

	@Autowired
	private RoutesService routesService;

	@Test
	public void getRoutesByDepartureTest(){
		
		RoutesInfoDto routesInfo = routesService.getRoutesByDeparture();
		
		Assert.assertEquals(true, !routesInfo.getRoutesByDepartureMap().isEmpty());				
	}

}
