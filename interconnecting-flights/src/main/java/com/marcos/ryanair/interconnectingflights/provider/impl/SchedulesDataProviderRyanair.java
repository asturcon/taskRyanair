package com.marcos.ryanair.interconnectingflights.provider.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.provider.RestConnectorManager;
import com.marcos.ryanair.interconnectingflights.provider.SchedulesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.provider.exception.RestConnectionException;
import com.marcos.ryanair.interconnectingflights.service.adapter.SchedulesAdapter;
import com.marcos.ryanair.interconnectingflights.service.dto.SchedulesDto;

/**
 * Implementation of {@link SchedulesDataProvider}:
 * 
 * @author mlg
 *
 */
@Service("schedulesDataProvider")
public class SchedulesDataProviderRyanair implements SchedulesDataProvider {

	@Autowired
	private RestConnectorManager restConnectorManager;

	@Autowired
	private SchedulesAdapter schedulesAdapter;

	@Override
	public SchedulesDto getFlightsSchedule(String departure, String arrival, int year, int month)
			throws DataProviderException {

		Map<String, String> params = new HashMap<>();
		params.put("departure", departure);
		params.put("arrival", arrival);
		params.put("year", String.valueOf(year));
		params.put("month", String.valueOf(month));

		// TODO: sacar url a properties.
		JsonNode jsonNode;
		try {
			jsonNode = restConnectorManager.getRestResponseAsJson(
					"https://api.ryanair.com/timetable/3/schedules/{departure}/{arrival}/years/{year}/months/{month}",
					params);
		} catch (RestConnectionException ex) {
			throw new DataProviderException("Could not fetch the schedules from the provider", ex);
		}

		return schedulesAdapter.adaptSchedules(jsonNode, year);
	}

}
