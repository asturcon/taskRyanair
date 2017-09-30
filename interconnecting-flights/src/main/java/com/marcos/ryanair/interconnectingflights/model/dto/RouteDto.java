package com.marcos.ryanair.interconnectingflights.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class RouteDto implements Serializable {

	private static final long serialVersionUID = 3897681433236316147L;

	/**
	 * All airports of the route, including departure and arrival.
	 */
	private List<String> airports;	

	public List<String> getAirports() {
		return airports;
	}

	public void setAirports(List<String> airports) {
		this.airports = airports;
	}

	public int getStops() {
		return airports != null ? airports.size() - 2 : 0;
	}
	
	@Override
	public String toString() {		
		return Arrays.toString(airports.toArray());
	}
}
