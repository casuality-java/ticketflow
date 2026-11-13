package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.event;

import com.kerher.projects.ticketflow.catalog.domain.Event;
import com.kerher.projects.ticketflow.catalog.infrastructure.persistence.venue.VenueJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "venueId", source = "venue.id")
    Event toDomain(EventJpaEntity eventJpaEntity);

    @Mapping(target = "venue", source = "venueId", qualifiedByName = "venueFromId")
    EventJpaEntity toJpaEntity(Event event);

    @Named("venueFromId")
    default VenueJpaEntity venueFromId(UUID venueId) {
        if (venueId == null) {
            return null;
        }
        return VenueJpaEntity.builder().id(venueId).build();
    }
}
