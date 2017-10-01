package com.marcos.ryanair.interconnectingflights.adapter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesDto;

@Component
public interface SchedulesAdapter {

	public SchedulesDto adaptSchedules(JsonNode jsonNode, int year);

}
