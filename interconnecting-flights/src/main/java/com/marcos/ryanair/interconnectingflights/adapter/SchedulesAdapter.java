package com.marcos.ryanair.interconnectingflights.adapter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcos.ryanair.interconnectingflights.model.dto.SchedulesInfoDto;

@Component
public interface SchedulesAdapter {

	public SchedulesInfoDto adaptSchedules(JsonNode jsonNode);

}
