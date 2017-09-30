package com.marcos.ryanair.interconnectingflights.adapter.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.adapter.SchedulesAdapter;
import com.marcos.ryanair.interconnectingflights.model.dto.FlightScheduleDto;
import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesInfoDto;

@Component("schedulesAdapter")
public class SchedulesAdapterImpl implements SchedulesAdapter {

	@Override
	public SchedulesInfoDto adaptSchedules(JsonNode jsonNode) {

		SchedulesInfoDto schedulesInfo = new SchedulesInfoDto();
		Map<Integer, Map<Integer, List<FlightScheduleDto>>> schedules = new HashMap<>();
		schedulesInfo.setSchedules(schedules);

		Integer month = jsonNode.get("month").asInt();
		Map<Integer, List<FlightScheduleDto>> daysMap = new HashMap<>();
		schedules.put(month, daysMap);

		for (JsonNode dayElement : jsonNode.get("days")) {
			Integer day = dayElement.get("day").asInt();
			List<FlightScheduleDto> fligths = new ArrayList<>();
			daysMap.put(day, fligths);

			for (JsonNode flightElement : dayElement.get("flights")) {
				FlightScheduleDto flightSchedule = new FlightScheduleDto();
				flightSchedule.setDepartureTime(LocalTime.parse(flightElement.get("departureTime").asText()));
				flightSchedule.setArrivalTime(LocalTime.parse(flightElement.get("arrivalTime").asText()));
				fligths.add(flightSchedule);
			}
		}

		return schedulesInfo;
	}

}
