package com.marcos.ryanair.interconnectingflights.view.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.ryanair.interconnectingflights.facade.FlightsFinderFacade;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.service.exception.ServiceException;

@RestController
public class InterconnetingFlightsController {

	@Autowired
	private FlightsFinderFacade flightsFinderFacade;

	@GetMapping(value = "/interconnections", params = { "departure", "arrival", "departureDateTime",
			"arrivalDateTime" })
	public FlightsInfo interconnectingFlights(HttpServletRequest request, @RequestParam("departure") String departure,
			@RequestParam("arrival") String arrival,
			@RequestParam("departureDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
			@RequestParam("arrivalDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime) {

		FlightsInfo flightsInfo = null;
		try {
			flightsInfo = flightsFinderFacade.findFlights(departure, arrival, departureDateTime,
					arrivalDateTime, 1, Duration.ofHours(2));
		} catch (ServiceException e) {
			
		}

		System.out.println(flightsInfo);

		return flightsInfo;
	}

}
