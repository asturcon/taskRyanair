package com.marcos.ryanair.interconnectingflights.view.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;
import com.marcos.ryanair.interconnectingflights.view.adapter.FlightsInfoViewAdapter;
import com.marcos.ryanair.interconnectingflights.view.model.FlightView;

/**
 * Controller that handles the request for interconnecting flights.
 * 
 * @author mlg
 *
 */
@RestController
public class InterconnetingFlightsController {

	private static final Logger LOG = LoggerFactory.getLogger(InterconnetingFlightsController.class);

	private static final int MAX_STOPS = 1;
	private static final Duration MIN_TIME_STOP = Duration.ofHours(2);

	@Autowired
	private FlightsFinderFacade flightsFinderFacade;

	@Autowired
	private FlightsInfoViewAdapter flightsInfoViewAdapter;

	@GetMapping(value = "/interconnections", params = { "departure", "arrival", "departureDateTime",
			"arrivalDateTime" })
	public List<FlightView> interconnectingFlights(HttpServletRequest request,
			@RequestParam("departure") String departure, @RequestParam("arrival") String arrival,
			@RequestParam("departureDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
			@RequestParam("arrivalDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime) {

		FlightsInfo flightsInfo = null;
		try {
			flightsInfo = flightsFinderFacade.findFlights(departure, arrival, departureDateTime, arrivalDateTime,
					MAX_STOPS, MIN_TIME_STOP);
		} catch (ServiceException ex) {
			LOG.error("No flights found", ex);
		}

		if (flightsInfo == null) {
			return new ArrayList<>();
		}

		return flightsInfoViewAdapter.adaptFlightsInfo(flightsInfo);
	}

}
