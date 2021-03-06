package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Models a leg of a flight.
 * 
 * @author mlg
 *
 */
public class Leg implements Serializable {

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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(getDepartureAirport());
		sb.append("-");
		sb.append(getArrivalAirport());
		sb.append("  ");
		sb.append(getDepartureTime());
		sb.append(" - ");
		sb.append(getArrivalTime());

		return sb.toString();
	}

}
