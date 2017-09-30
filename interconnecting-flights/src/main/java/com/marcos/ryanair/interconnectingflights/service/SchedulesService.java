package com.marcos.ryanair.interconnectingflights.service;

import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesInfoDto;

public interface SchedulesService {

	public SchedulesInfoDto getFlightsSchedule(String departure, String arrival, int year, int month);

}
