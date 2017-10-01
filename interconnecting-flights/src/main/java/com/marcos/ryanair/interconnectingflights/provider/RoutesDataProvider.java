package com.marcos.ryanair.interconnectingflights.provider;

import com.marcos.ryanair.interconnectingflights.model.dto.RoutesInfoDto;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;

/**
 * Defines the methods to interact with the routes data provider.
 * 
 * @author mlg
 *
 */
public interface RoutesDataProvider {

	/**
	 * Get routes grouped by the departure airport.
	 * 
	 * @return {@link RoutesInfoDto}
	 * @throws DataProviderException
	 */
	public RoutesInfoDto getRoutesGroupedByDeparture() throws DataProviderException;

	/**
	 * Get routes grouped by the arrival airport.
	 * 
	 * @return {@link RoutesInfoDto}
	 * @throws DataProviderException
	 */
	public RoutesInfoDto getRoutesGroupedByArrival() throws DataProviderException;

}
