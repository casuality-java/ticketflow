package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.event;

import com.kerher.projects.ticketflow.catalog.domain.Event;
import com.kerher.projects.ticketflow.catalog.domain.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    private final EventMapper eventMapper;

    @Override
    public Event save(Event event) {
        return eventMapper.toDomain(eventJpaRepository.save(eventMapper.toJpaEntity(event)));
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return eventJpaRepository.findById(id).map(eventMapper::toDomain);
    }
}
