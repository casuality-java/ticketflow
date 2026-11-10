package com.kerher.projects.ticketflow.catalog.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Event {

    private UUID id;

    private String title;

    private String description;

    private UUID venueId;

    private EventCategory category;

}
