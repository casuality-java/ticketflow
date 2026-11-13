package com.kerher.projects.ticketflow.catalog.infrastructure.web.venue.dto;

import com.kerher.projects.ticketflow.catalog.domain.EventCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateEventRequest(
        @NotBlank(message = "title cannot be blank") String title,
        String description,
        @NotNull(message = "venueId is required") UUID venueId,
        @NotNull(message = "category is required") EventCategory category) {}
