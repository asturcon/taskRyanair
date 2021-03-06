package com.marcos.ryanair.interconnectingflights.provider.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marcos.ryanair.interconnectingflights.provider.RoutesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.service.dto.RoutesInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class RoutesDataProviderRyanairTest {

	@Autowired
	private RoutesDataProvider routesDataProvider;

	@Test
	@Ignore
	public void getRoutesByDepartureTest(){
		
		RoutesInfoDto routesInfo = null;
		try {
			routesInfo = routesDataProvider.getRoutesGroupedByDeparture();
		} catch (DataProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, !routesInfo.getRoutesByDepartureMap().isEmpty());				
	}

}
