package com.marcos.ryanair.interconnectingflights.provider;

import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesDto;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;

/**
 * Defines the methods to interact with the schedules provider.
 * 
 * @author mlg
 *
 */
public interface SchedulesDataProvider {

	/**
	 * Fetchs the schedules for a flight.
	 * 
	 * @param departure
	 * @param arrival
	 * @param year
	 * @param month
	 * @return
	 * @throws DataProviderException
	 */
	public SchedulesDto getFlightsSchedule(String departure, String arrival, int year, int month)
			throws DataProviderException;

}
