package com.kerher.projects.ticketflow.catalog.application.venue;

import lombok.Builder;

@Builder
public record CreateVenueCommand(String name, String address, String city) {}
