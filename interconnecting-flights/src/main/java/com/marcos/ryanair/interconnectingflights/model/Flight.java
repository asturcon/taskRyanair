package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {

	private static final long serialVersionUID = -8887400840876314320L;

	private String departureAirport;
	
	private String arrivalAirport;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
