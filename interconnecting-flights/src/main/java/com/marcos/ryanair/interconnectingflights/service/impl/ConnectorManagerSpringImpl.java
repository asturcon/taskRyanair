package com.marcos.ryanair.interconnectingflights.service.impl;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.ryanair.interconnectingflights.service.ConnectorManager;

@Component
public class ConnectorManagerSpringImpl implements ConnectorManager {

	@Override
	public <T> ResponseEntity<T> getRestResponse(String url, Class<T> clazz) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> response = restTemplate.getForEntity(url, clazz);

		return response;
	}

	@Override
	public JsonNode getRestResponseAsJson(String url) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return root;
	}

}
