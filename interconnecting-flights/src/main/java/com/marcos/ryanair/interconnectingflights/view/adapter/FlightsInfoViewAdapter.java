package com.marcos.ryanair.interconnectingflights.view.adapter;

import java.util.List;

import com.marcos.ryanair.interconnectingflights.model.FlightsInfo;
import com.marcos.ryanair.interconnectingflights.view.model.FlightView;

/**
 * Adapts the flight model to the view.
 * 
 * @author mlg
 *
 */
public interface FlightsInfoViewAdapter {

	public List<FlightView> adaptFlightsInfo(FlightsInfo flightsInfo);

}
