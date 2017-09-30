package com.marcos.ryanair.interconnectingflights.model.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class FlightScheduleDto implements Serializable {

	private static final long serialVersionUID = 6570382591167181383L;

	private LocalTime departureTime;
	
	private LocalTime arrivalTime;

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
