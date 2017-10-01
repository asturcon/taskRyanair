package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Contains the info about the interconnecting flights.
 * 
 * @author mlg
 *
 */
public class FlightsInfo implements Serializable {

	private static final long serialVersionUID = 4843337507789973590L;

	/**
	 * key -> number of stops, value -> available flights
	 */
	private Map<Integer, List<Flight>> interconnectingFlights;

	public Map<Integer, List<Flight>> getInterconnectingFlights() {
		return interconnectingFlights;
	}

	public void setInterconnectingFlights(Map<Integer, List<Flight>> interconnectingFlights) {
		this.interconnectingFlights = interconnectingFlights;
	}

}
