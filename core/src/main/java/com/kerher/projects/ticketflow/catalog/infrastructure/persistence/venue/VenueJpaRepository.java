package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.venue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueJpaRepository extends JpaRepository<VenueJpaEntity, UUID> {
}
