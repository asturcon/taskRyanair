package com.marcos.ryanair.interconnectingflights.service.adapter.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.service.adapter.SchedulesAdapter;
import com.marcos.ryanair.interconnectingflights.service.dto.FlightScheduleDto;
import com.marcos.ryanair.interconnectingflights.service.dto.SchedulesDto;

@Component("schedulesAdapter")
public class SchedulesAdapterImpl implements SchedulesAdapter {

	@Override
	public SchedulesDto adaptSchedules(JsonNode jsonNode, int year) {

		SchedulesDto schedulesInfo = new SchedulesDto();
		SortedSet<FlightScheduleDto> flights = new TreeSet<>();
		schedulesInfo.setFlights(flights);

		int month = jsonNode.get("month").asInt();
		for (JsonNode dayElement : jsonNode.get("days")) {
			for (JsonNode flightElement : dayElement.get("flights")) {
				int day = dayElement.get("day").asInt();

				LocalDateTime departureDateTime = LocalDateTime.of(LocalDate.of(year, month, day),
						LocalTime.parse(flightElement.get("departureTime").asText()));
				LocalDateTime arrivalDateTime = LocalDateTime.of(LocalDate.of(year, month, day),
						LocalTime.parse(flightElement.get("arrivalTime").asText()));

				FlightScheduleDto flightSchedule = new FlightScheduleDto();
				flightSchedule.setDepartureDateTime(departureDateTime);
				flightSchedule.setArrivalDateTime(arrivalDateTime);

				flights.add(flightSchedule);
			}
		}

		return schedulesInfo;
	}

}
