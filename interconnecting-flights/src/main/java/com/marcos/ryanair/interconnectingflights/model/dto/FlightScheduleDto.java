package com.marcos.ryanair.interconnectingflights.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for flight schedules.
 * 
 * @author mlg
 *
 */
public class FlightScheduleDto implements Serializable, Comparable<FlightScheduleDto> {

	private static final long serialVersionUID = 6570382591167181383L;

	private LocalDateTime departureDateTime;

	private LocalDateTime arrivalDateTime;

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
		result = prime * result + ((departureDateTime == null) ? 0 : departureDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightScheduleDto other = (FlightScheduleDto) obj;
		if (arrivalDateTime == null) {
			if (other.arrivalDateTime != null)
				return false;
		} else if (!arrivalDateTime.equals(other.arrivalDateTime))
			return false;
		if (departureDateTime == null) {
			if (other.departureDateTime != null)
				return false;
		} else if (!departureDateTime.equals(other.departureDateTime))
			return false;
		return true;
	}

	@Override
	public int compareTo(FlightScheduleDto o) {
		if (o == null) {
			return 1;
		}

		if (this.departureDateTime == null && o.getDepartureDateTime() == null) {
			return 0;
		} else if (this.departureDateTime == null) {
			return -1;
		} else if (o.getDepartureDateTime() == null) {
			return 1;
		} else {
			return this.departureDateTime.compareTo(o.getDepartureDateTime());
		}
	}

}
