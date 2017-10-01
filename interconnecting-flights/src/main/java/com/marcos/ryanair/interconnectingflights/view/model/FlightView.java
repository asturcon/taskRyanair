package com.marcos.ryanair.interconnectingflights.view.model;

import java.io.Serializable;
import java.util.List;

public class FlightView implements Serializable {

	private static final long serialVersionUID = 3545787516124921812L;

	private int stops;
	
	private List<LegView> legs;

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public List<LegView> getLegs() {
		return legs;
	}

	public void setLegs(List<LegView> legs) {
		this.legs = legs;
	}		
	
}
