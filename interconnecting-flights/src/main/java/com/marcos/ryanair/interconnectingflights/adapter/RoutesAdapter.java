package com.marcos.ryanair.interconnectingflights.adapter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.model.RoutesInfo;

// TODO: hacer interface
@Component
public class RoutesAdapter {

	public RoutesInfo adaptRoutesInfo(JsonNode json) {
		RoutesInfo routesInfo = new RoutesInfo();
		
		
		
		return routesInfo;
	}
	
	
}
