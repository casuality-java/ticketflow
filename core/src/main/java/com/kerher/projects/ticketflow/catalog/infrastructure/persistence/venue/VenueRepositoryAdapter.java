package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.venue;

import com.kerher.projects.ticketflow.catalog.domain.Venue;
import com.kerher.projects.ticketflow.catalog.domain.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VenueRepositoryAdapter implements VenueRepository {

    private final VenueJpaRepository venueJpaRepository;

    private final VenueMapper venueMapper;

    @Override
    public Venue save(Venue venue) {
        return venueMapper.toDomain(venueJpaRepository.save(venueMapper.toEntity(venue)));
    }

    @Override
    public Optional<Venue> findById(UUID id) {
        return venueJpaRepository.findById(id).map(venueMapper::toDomain);
    }
}
