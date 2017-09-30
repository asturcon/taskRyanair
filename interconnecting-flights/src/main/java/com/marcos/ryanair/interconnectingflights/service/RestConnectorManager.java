package com.marcos.ryanair.interconnectingflights.service;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Defines a manager to connect to REST WS.
 * 
 * @author mlg
 *
 */
public interface RestConnectorManager {

	/**
	 * Gets the response from a rest ws as JSON.
	 * 
	 * @param url
	 *            url of the request.
	 * @return {@link JsonNode} with the response.
	 */
	public JsonNode getRestResponseAsJson(String url);

	/**
	 * Gets the response from a rest ws as JSON. It receives the parameters in a
	 * separate argument.
	 * 
	 * @param url
	 *            url of request with the placeholders for the parameters.
	 * @param parameters
	 *            map with the name of the parameters and their values.
	 * @return {@link JsonNode} with the response.
	 */
	public JsonNode getRestResponseAsJson(String url, Map<String, String> parameters);

}
