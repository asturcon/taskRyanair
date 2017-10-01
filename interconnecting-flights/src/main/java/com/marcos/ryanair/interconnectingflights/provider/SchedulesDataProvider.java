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
	 *            departure airport
	 * @param arrival
	 *            arrival airport
	 * @param year
	 *            year of the schedule requested
	 * @param month
	 *            month of the schedule requested
	 * @return {@link SchedulesDto}
	 * @throws DataProviderException
	 */
	public SchedulesDto getFlightsSchedule(String departure, String arrival, int year, int month)
			throws DataProviderException;

}
