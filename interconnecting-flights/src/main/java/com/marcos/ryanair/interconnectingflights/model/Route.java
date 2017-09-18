package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;

public class Route implements Serializable {

	private static final long serialVersionUID = 2620431996391831428L;

	private String origin;
	
	private String destination;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
