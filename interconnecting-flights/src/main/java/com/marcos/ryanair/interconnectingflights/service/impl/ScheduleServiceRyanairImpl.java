package com.marcos.ryanair.interconnectingflights.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.adapter.SchedulesAdapter;
import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesInfoDto;
import com.marcos.ryanair.interconnectingflights.service.RestConnectorManager;
import com.marcos.ryanair.interconnectingflights.service.SchedulesService;

@Service("schedulesService")
public class ScheduleServiceRyanairImpl implements SchedulesService {

	@Autowired
	private RestConnectorManager restConnectorManager;

	@Autowired
	private SchedulesAdapter schedulesAdapter;

	@Override
	public SchedulesInfoDto getFlightsSchedule(String departure, String arrival, int year, int month) {

		Map<String, String> params = new HashMap<>();
		params.put("departure", departure);
		params.put("arrival", arrival);
		params.put("year", String.valueOf(year));
		params.put("month", String.valueOf(month));

		// TODO: sacar url a properties.
		JsonNode jsonNode = restConnectorManager.getRestResponseAsJson(
				"https://api.ryanair.com/timetable/3/schedules/{departure}/{arrival}/years/{year}/months/{month}",
				params);

		return schedulesAdapter.adaptSchedules(jsonNode);
	}

}
