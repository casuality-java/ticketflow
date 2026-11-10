package com.kerher.projects.ticketflow.catalog.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class EventSession {

    private UUID id;

    private UUID eventId;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;
}
