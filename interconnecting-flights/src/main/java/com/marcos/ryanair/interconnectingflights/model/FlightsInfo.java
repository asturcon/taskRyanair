package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FlightsInfo implements Serializable {

	private static final long serialVersionUID = 4843337507789973590L;

	private Map<Integer, List<Flight>> interconnectingFlights;

	public Map<Integer, List<Flight>> getInterconnectingFlights() {
		return interconnectingFlights;
	}

	public void setInterconnectingFlights(Map<Integer, List<Flight>> interconnectingFlights) {
		this.interconnectingFlights = interconnectingFlights;
	}		
	
}
