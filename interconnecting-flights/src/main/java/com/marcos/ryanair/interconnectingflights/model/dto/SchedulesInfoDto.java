package com.marcos.ryanair.interconnectingflights.model.dto;

import java.util.List;
import java.util.Map;

public class SchedulesInfoDto {

	private Map<Integer, Map<Integer, List<FlightScheduleDto>>> schedules;

	public Map<Integer, Map<Integer, List<FlightScheduleDto>>> getSchedules() {
		return schedules;
	}

	public void setSchedules(Map<Integer, Map<Integer, List<FlightScheduleDto>>> schedules) {
		this.schedules = schedules;
	}

	public Map<Integer, List<FlightScheduleDto>> getSchedulesByMonth(int month) {
		if (schedules != null) {
			return schedules.get(month);
		}

		return null;
	}

	public List<FlightScheduleDto> getSchedulesByMonthAndDay(int month, int day) {
		if (schedules != null && schedules.get(month) != null) {
			return schedules.get(month).get(day);
		}

		return null;
	}

}
