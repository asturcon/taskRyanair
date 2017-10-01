package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a Flight that contains one or more legs.
 * 
 * @author mlg
 *
 */
public class Flight implements Serializable {

	private static final long serialVersionUID = -7859814014524128149L;

	private List<Leg> legs;

	private int stops;

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	protected void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	public void addLeg(Leg leg) {
		if (legs == null) {
			legs = new ArrayList<>();
		}

		legs.add(leg);
	}

}
