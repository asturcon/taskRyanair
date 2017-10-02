package com.marcos.ryanair.interconnectingflights.service.dto;

import java.util.Comparator;

/**
 * Compares @{@link FlightScheduleDto} by the arrival date-time.
 * 
 * @author mlg
 *
 */
public class ComparatorFlightScheduleDtoByArrivalDateTime implements Comparator<FlightScheduleDto> {

	@Override
	public int compare(FlightScheduleDto o1, FlightScheduleDto o2) {
		if (o1 == o2) {
			return 0;
		} else if (o1 == null) {
			return -1;
		} else if (o2 == null) {
			return 1;
		} else {
			if (o1.getArrivalDateTime() == null && o2.getArrivalDateTime() == null) {
				return 0;
			} else if (o1.getArrivalDateTime() == null) {
				return -1;
			} else if (o2.getArrivalDateTime() == null) {
				return 1;
			} else {
				return o1.getArrivalDateTime().compareTo(o2.getArrivalDateTime());
			}
		}

	}
}
