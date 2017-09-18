package com.marcos.ryanair.interconnectingflights.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class RoutesInfo implements Serializable {

	private static final long serialVersionUID = -1155045573441667470L;

	/**
	 * Key -> IATA code of origin
	 * Value -> destinations from the origin.
	 */
	private Map<String, Set<String>> routesByOriginMap;
	
	/**
	 * Key -> IATA code of destination
	 * Value -> origins that go to the destination.
	 */
	private Map<String, Set<String>> routesByDestinationMap;

	public Map<String, Set<String>> getRoutesByOriginMap() {
		return routesByOriginMap;
	}

	public void setRoutesByOriginMap(Map<String, Set<String>> routesByOriginMap) {
		this.routesByOriginMap = routesByOriginMap;
	}

	public Map<String, Set<String>> getRoutesByDestinationMap() {
		return routesByDestinationMap;
	}

	public void setRoutesByDestinationMap(Map<String, Set<String>> routesByDestinationMap) {
		this.routesByDestinationMap = routesByDestinationMap;
	}
	
}
