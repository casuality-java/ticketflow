package com.kerher.projects.ticketflow.catalog.application.event;

import com.kerher.projects.ticketflow.catalog.domain.EventCategory;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateEventCommand(String title, String description, UUID venueId, EventCategory category) {
}
