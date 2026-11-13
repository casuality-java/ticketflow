package com.kerher.projects.ticketflow.catalog.domain;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID id);
}
