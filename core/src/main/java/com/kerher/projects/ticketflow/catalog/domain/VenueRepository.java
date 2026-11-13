package com.kerher.projects.ticketflow.catalog.domain;

import java.util.Optional;
import java.util.UUID;

public interface VenueRepository {
    Venue save(Venue venue);
    Optional<Venue> findById(UUID id);
    boolean existsById(UUID id);
}
