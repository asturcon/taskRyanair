package com.marcos.ryanair.interconnectingflights.provider;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.provider.exception.RestConnectionException;

/**
 * Defines a manager to connect to a REST WS.
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
	 * @throws RestConnectionException
	 *             if there was an error.
	 */
	public JsonNode getRestResponseAsJson(String url) throws RestConnectionException;

	/**
	 * Gets the response from a rest ws as JSON. It receives the parameters in a
	 * separate argument.
	 * 
	 * @param url
	 *            url of request with the placeholders for the parameters.
	 * @param parameters
	 *            map with the name of the parameters and their values.
	 * @return {@link JsonNode} with the response.
	 * @throws RestConnectionException
	 *             if there was an error.
	 */
	public JsonNode getRestResponseAsJson(String url, Map<String, String> parameters) throws RestConnectionException;

}
