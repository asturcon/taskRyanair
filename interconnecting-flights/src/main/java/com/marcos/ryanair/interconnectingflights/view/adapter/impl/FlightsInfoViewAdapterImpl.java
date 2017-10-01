package com.marcos.ryanair.interconnectingflights.view.adapter.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.model.Leg;
import com.marcos.ryanair.interconnectingflights.view.adapter.FlightsInfoViewAdapter;
import com.marcos.ryanair.interconnectingflights.view.model.FlightView;
import com.marcos.ryanair.interconnectingflights.view.model.LegView;

@Component("flightsInfoViewAdapter")
public class FlightsInfoViewAdapterImpl implements FlightsInfoViewAdapter {

	private static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm";

	@Override
	public List<FlightView> adaptFlightsInfo(FlightsInfo flightsInfo) {
		List<FlightView> flightViews = new ArrayList<>();

		if (flightsInfo != null && flightsInfo.getInterconnectingFlights() != null) {
			for (int stops : flightsInfo.getInterconnectingFlights().keySet()) {
				for (Flight flight : flightsInfo.getInterconnectingFlights().get(stops)) {
					FlightView flightView = new FlightView();
					flightView.setStops(stops);

					List<LegView> legViews = new ArrayList<>();
					flightView.setLegs(legViews);
					for (Leg leg : flight.getLegs()) {
						LegView legView = new LegView();
						legView.setDepartureAirport(leg.getDepartureAirport());
						legView.setArrivalAirport(leg.getArrivalAirport());

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_PATTERN);

						legView.setDepartureDateTime(leg.getDepartureTime().format(formatter));
						legView.setArrivalDateTime(leg.getArrivalTime().format(formatter));
						legViews.add(legView);
					}

					flightViews.add(flightView);
				}
			}
		}

		return flightViews;
	}

}
