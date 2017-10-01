package com.marcos.ryanair.interconnectingflights.model.dto;

import java.util.SortedSet;

public class SchedulesDto {

	private SortedSet<FlightScheduleDto> flights;

	public SortedSet<FlightScheduleDto> getFlights() {
		return flights;
	}

	public void setFlights(SortedSet<FlightScheduleDto> flights) {
		this.flights = flights;
	}

}
