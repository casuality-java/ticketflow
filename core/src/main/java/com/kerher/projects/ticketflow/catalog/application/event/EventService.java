package com.kerher.projects.ticketflow.catalog.application.event;

import com.kerher.projects.ticketflow.catalog.domain.Event;
import com.kerher.projects.ticketflow.catalog.domain.EventNotFoundException;
import com.kerher.projects.ticketflow.catalog.domain.EventRepository;
import com.kerher.projects.ticketflow.catalog.domain.VenueNotFoundException;
import com.kerher.projects.ticketflow.catalog.domain.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final VenueRepository venueRepository;

    @Transactional
    public Event createEvent(CreateEventCommand command) {
        if (!venueRepository.existsById(command.venueId())) {
            throw new VenueNotFoundException(command.venueId());
        }
        return eventRepository.save(Event.builder()
                .title(command.title())
                .description(command.description())
                .venueId(command.venueId())
                .category(command.category())
                .build());
    }

    @Transactional
    public Event getEventById(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }


}
