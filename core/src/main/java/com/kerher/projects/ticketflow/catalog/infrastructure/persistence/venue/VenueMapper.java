package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.venue;

import com.kerher.projects.ticketflow.catalog.domain.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    Venue toDomain(VenueJpaEntity venueJpaEntity);

    @Mapping(target = "events", ignore = true)
    VenueJpaEntity toEntity(Venue venue);
}
