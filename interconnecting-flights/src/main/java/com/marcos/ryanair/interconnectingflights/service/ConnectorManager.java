package com.marcos.ryanair.interconnectingflights.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;

public interface ConnectorManager {

	public <T> ResponseEntity<T> getRestResponse(String url, Class<T> clazz);	
		
	public JsonNode getRestResponseAsJson(String url);
	
}
