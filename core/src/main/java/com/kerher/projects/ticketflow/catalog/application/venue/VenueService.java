package com.kerher.projects.ticketflow.catalog.application.venue;

import com.kerher.projects.ticketflow.catalog.domain.Venue;
import com.kerher.projects.ticketflow.catalog.domain.VenueNotFoundException;
import com.kerher.projects.ticketflow.catalog.domain.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    @Transactional
    public Venue createVenue(CreateVenueCommand command) {
        Venue venue = Venue.builder()
                .name(command.name())
                .address(command.address())
                .city(command.city())
                .build();
        return venueRepository.save(venue);
    }

    @Transactional
    public Venue getVenueById(UUID id) {
        return venueRepository.findById(id).orElseThrow(() -> new VenueNotFoundException(id));
    }
}
