package com.marcos.ryanair.interconnectingflights.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.ryanair.interconnectingflights.model.Flight;
import com.marcos.ryanair.interconnectingflights.model.Leg;
import com.marcos.ryanair.interconnectingflights.model.Route;
import com.marcos.ryanair.interconnectingflights.model.dto.FlightScheduleDto;
import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesDto;
import com.marcos.ryanair.interconnectingflights.provider.SchedulesDataProvider;
import com.marcos.ryanair.interconnectingflights.provider.exception.DataProviderException;
import com.marcos.ryanair.interconnectingflights.service.SchedulesService;

@Service("schedulesService")
public class SchedulesServiceImpl implements SchedulesService {

	private static final Logger LOG = LoggerFactory.getLogger(SchedulesServiceImpl.class);

	@Autowired
	private SchedulesDataProvider schedulesDataProvider;

	@Override
	public List<Flight> findRouteFlightsMax1Stop(Route route, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, Duration minTimeStop) {

		List<Flight> totalFlights = new ArrayList<>();

		if (route.getAirports().size() == 2) {
			// direct flight
			NavigableSet<FlightScheduleDto> flightsScheduled = findSchedules(route.getAirports().get(0),
					route.getAirports().get(1), departureDateTime, arrivalDateTime);

			for (FlightScheduleDto flightDto : flightsScheduled) {
				Leg leg = new Leg();
				leg.setDepartureAirport(route.getAirports().get(0));
				leg.setArrivalAirport(route.getAirports().get(1));
				leg.setDepartureTime(flightDto.getDepartureDateTime());
				leg.setArrivalTime(flightDto.getArrivalDateTime());

				Flight flight = new Flight();
				flight.addLeg(leg);
				flight.setStops(0);
				totalFlights.add(flight);
			}

		} else if (route.getAirports().size() == 3) {
			// flights with 1 stop
			NavigableSet<FlightScheduleDto> flightsScheduledFirstLeg = findSchedules(route.getAirports().get(0),
					route.getAirports().get(1), departureDateTime, arrivalDateTime);

			NavigableSet<FlightScheduleDto> flightsScheduledSecondLeg = findSchedules(route.getAirports().get(1),
					route.getAirports().get(2), departureDateTime, arrivalDateTime);

			for (FlightScheduleDto flightDtoFirstLeg : flightsScheduledFirstLeg) {
				FlightScheduleDto flightAuxDtoMinTimeStop = new FlightScheduleDto();
				flightAuxDtoMinTimeStop.setDepartureDateTime(flightDtoFirstLeg.getArrivalDateTime().plus(minTimeStop));

				for (FlightScheduleDto flightDtoSecondLeg : tailSetRangeSafe(flightsScheduledSecondLeg,
						flightAuxDtoMinTimeStop)) {
					// create the flights
					Flight flight = new Flight();
					flight.setStops(1);

					// first leg
					Leg firstLeg = new Leg();
					firstLeg.setDepartureAirport(route.getAirports().get(0));
					firstLeg.setArrivalAirport(route.getAirports().get(1));
					firstLeg.setDepartureTime(flightDtoFirstLeg.getDepartureDateTime());
					firstLeg.setArrivalTime(flightDtoFirstLeg.getArrivalDateTime());
					flight.addLeg(firstLeg);

					// second leg
					Leg secondLeg = new Leg();
					secondLeg.setDepartureAirport(route.getAirports().get(1));
					secondLeg.setArrivalAirport(route.getAirports().get(2));
					secondLeg.setDepartureTime(flightDtoSecondLeg.getDepartureDateTime());
					secondLeg.setArrivalTime(flightDtoSecondLeg.getArrivalDateTime());
					flight.addLeg(secondLeg);

					totalFlights.add(flight);
				}
			}

		}

		return totalFlights;
	}

	// TODO: utils
	private NavigableSet<FlightScheduleDto> tailSetRangeSafe(NavigableSet<FlightScheduleDto> setToCheck,
			FlightScheduleDto dtoFilter) {

		if (setToCheck == null || setToCheck.isEmpty()) {
			return new TreeSet<>();
		}

		if (dtoFilter.compareTo(setToCheck.first()) <= 0) {
			return setToCheck;
		} else if (dtoFilter.compareTo(setToCheck.last()) > 0) {
			return new TreeSet<>();
		} else {
			return setToCheck.tailSet(dtoFilter, true);
		}

	}

	// TODO: utils
	private NavigableSet<FlightScheduleDto> headSetRangeSafeWithSetComparator(
			NavigableSet<FlightScheduleDto> setToCheck, FlightScheduleDto dtoFilter) {

		if (setToCheck == null || setToCheck.isEmpty()) {
			return new TreeSet<>();
		}

		if (setToCheck.comparator().compare(dtoFilter, setToCheck.first()) <= 0) {
			return new TreeSet<>();
		} else if (setToCheck.comparator().compare(dtoFilter, setToCheck.last()) > 0) {
			return setToCheck;
		} else {
			return setToCheck.headSet(dtoFilter, true);
		}

	}

	private NavigableSet<FlightScheduleDto> findSchedules(String arrival, String departure,
			LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {

		// find all the calls i have to make to the schedule provider.
		Set<SchedulePossibility> possibilities = findSchedulePossibilities(departureDateTime, arrivalDateTime);

		NavigableSet<FlightScheduleDto> totalFlights = new TreeSet<>();

		for (SchedulePossibility possibility : possibilities) {
			// get schedules
			try {
				SchedulesDto schedulesDto = schedulesDataProvider.getFlightsSchedule(departure, arrival,
						possibility.year, possibility.month);

				totalFlights.addAll(schedulesDto.getFlights());
			} catch (DataProviderException e) {
				// keep checking
				LOG.debug("No schedules found for departure {}, arrival {}, year {} and month {}", departure, arrival,
						possibility.year, possibility.month);
			}
		}

		// filter by the dates
		NavigableSet<FlightScheduleDto> flightsFiltered = filterFlightsByDates(totalFlights, departureDateTime,
				arrivalDateTime);

		return flightsFiltered;
	}

	private NavigableSet<FlightScheduleDto> filterFlightsByDates(NavigableSet<FlightScheduleDto> totalFlights,
			LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {

		// get fligts that arrive not earlier than departure time
		FlightScheduleDto departureAuxDto = new FlightScheduleDto();
		departureAuxDto.setDepartureDateTime(departureDateTime);

		NavigableSet<FlightScheduleDto> flightsDepartureOnTime = tailSetRangeSafe(totalFlights, departureAuxDto);

		// get flights than arrive not later than arrival time
		NavigableSet<FlightScheduleDto> totalFlightsComparatorByArrival = new TreeSet<>(
				new Comparator<FlightScheduleDto>() {

					@Override
					public int compare(FlightScheduleDto o1, FlightScheduleDto o2) {
						if (o1 == o2) {
							return 0;
						} else if (o1 == null) {
							return -1;
						} else if (o2 == null) {
							return 1;
						} else {
							if (o1.getArrivalDateTime() == null && o2.getArrivalDateTime() == null) {
								return 0;
							} else if (o1.getArrivalDateTime() == null) {
								return -1;
							} else if (o2.getArrivalDateTime() == null) {
								return 1;
							} else {
								return o1.getArrivalDateTime().compareTo(o2.getArrivalDateTime());
							}
						}
					}
				});

		totalFlightsComparatorByArrival.addAll(totalFlights);

		FlightScheduleDto arrivalAuxDto = new FlightScheduleDto();
		arrivalAuxDto.setArrivalDateTime(arrivalDateTime);
		NavigableSet<FlightScheduleDto> flightsArrivalOnTime = headSetRangeSafeWithSetComparator(
				totalFlightsComparatorByArrival, arrivalAuxDto);

		flightsDepartureOnTime.retainAll(flightsArrivalOnTime);

		return flightsDepartureOnTime;
	}

	private Set<SchedulePossibility> findSchedulePossibilities(LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime) {

		Set<SchedulePossibility> possibilities = new HashSet<>();

		int currentMonth = departureDateTime.getMonthValue();
		int currentYear = departureDateTime.getYear();
		int monthEnd = arrivalDateTime.getMonthValue();
		int yearEnd = arrivalDateTime.getYear();

		while (currentMonth <= monthEnd && currentYear <= yearEnd) {
			possibilities.add(new SchedulePossibility(currentMonth, currentYear));

			if (currentMonth == 12) {
				currentYear++;
			}

			currentMonth++;
		}

		return possibilities;
	}

	private static class SchedulePossibility {
		int month;
		int year;

		public SchedulePossibility(int month, int year) {
			this.month = month;
			this.year = year;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + month;
			result = prime * result + year;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SchedulePossibility other = (SchedulePossibility) obj;
			if (month != other.month)
				return false;
			if (year != other.year)
				return false;
			return true;
		}
	}

}
