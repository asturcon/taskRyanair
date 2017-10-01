package com.marcos.ryanair.interconnectingflights.view.model;

import java.io.Serializable;

/**
 * Models leg in the view layer.
 * 
 * @author mlg
 *
 */
public class LegView implements Serializable {

	private static final long serialVersionUID = -844726589825858347L;

	private String departureAirport;

	private String arrivalAirport;

	private String departureDateTime;

	private String arrivalDateTime;

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

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

}
