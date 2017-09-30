package com.marcos.ryanair.interconnectingflights.model.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class RoutesInfoDto implements Serializable {

	private static final long serialVersionUID = -1155045573441667470L;

	private Map<String, Set<String>> routesByDepartureMap;
	
	private Map<String, Set<String>> routesByArrivalMap;

	public Map<String, Set<String>> getRoutesByDepartureMap() {
		return routesByDepartureMap;
	}

	public void setRoutesByDepartureMap(Map<String, Set<String>> routesByDepartureMap) {
		this.routesByDepartureMap = routesByDepartureMap;
	}

	public Map<String, Set<String>> getRoutesByArrivalMap() {
		return routesByArrivalMap;
	}

	public void setRoutesByArrivalMap(Map<String, Set<String>> routesByArrivalMap) {
		this.routesByArrivalMap = routesByArrivalMap;
	}

		
}
