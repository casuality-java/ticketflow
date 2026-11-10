package com.kerher.projects.ticketflow.catalog.domain;

import java.util.UUID;

public class VenueNotFoundException extends RuntimeException {
    public VenueNotFoundException(UUID id) {
        super("Venue not found: " + id);
    }
}
