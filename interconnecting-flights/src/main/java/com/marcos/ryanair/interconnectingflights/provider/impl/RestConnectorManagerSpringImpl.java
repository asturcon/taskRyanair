package com.marcos.ryanair.interconnectingflights.provider.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.ryanair.interconnectingflights.provider.RestConnectorManager;
import com.marcos.ryanair.interconnectingflights.provider.exception.RestConnectionException;

@Component("restConnectorManager")
public class RestConnectorManagerSpringImpl implements RestConnectorManager {

	@Override
	public JsonNode getRestResponseAsJson(String url) throws RestConnectionException {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(url, String.class);
		} catch (RestClientException ex) {
			throw new RestConnectionException("Could not get the entity", ex);
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (IOException ex) {
			throw new RestConnectionException("Could not parse the response to JSON", ex);
		}

		return root;
	}

	@Override
	public JsonNode getRestResponseAsJson(String url, Map<String, String> parameters) throws RestConnectionException {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(url, String.class, parameters);
		} catch (RestClientException ex) {
			throw new RestConnectionException("Could not get the entity", ex);
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (IOException ex) {
			throw new RestConnectionException("Could not get the entity", ex);
		}

		return root;
	}

}
