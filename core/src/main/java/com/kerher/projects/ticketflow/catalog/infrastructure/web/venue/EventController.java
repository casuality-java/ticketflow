package com.kerher.projects.ticketflow.catalog.infrastructure.web.venue;

import com.kerher.projects.ticketflow.catalog.application.event.CreateEventCommand;
import com.kerher.projects.ticketflow.catalog.application.event.EventService;
import com.kerher.projects.ticketflow.catalog.domain.Event;
import com.kerher.projects.ticketflow.catalog.infrastructure.web.venue.dto.CreateEventRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public Event createEvent(@Valid @RequestBody CreateEventRequest cer) {
        return eventService.createEvent(CreateEventCommand.builder()
                .title(cer.title())
                .description(cer.description())
                .venueId(cer.venueId())
                .category(cer.category())
                .build());
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }
}
