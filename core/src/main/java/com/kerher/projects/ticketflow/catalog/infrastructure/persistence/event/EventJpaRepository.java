package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventJpaEntity, UUID> {
}
