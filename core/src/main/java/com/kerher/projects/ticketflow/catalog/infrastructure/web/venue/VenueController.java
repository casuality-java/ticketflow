package com.kerher.projects.ticketflow.catalog.infrastructure.web.venue;

import com.kerher.projects.ticketflow.catalog.application.venue.CreateVenueCommand;
import com.kerher.projects.ticketflow.catalog.application.venue.VenueService;
import com.kerher.projects.ticketflow.catalog.domain.Venue;
import com.kerher.projects.ticketflow.catalog.infrastructure.web.venue.dto.CreateVenueRequest;
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
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public Venue createVenue(@Valid @RequestBody CreateVenueRequest cvr) {
        return venueService.createVenue(CreateVenueCommand.builder()
                .name(cvr.name())
                .address(cvr.address())
                .city(cvr.city())
                .build()
        );
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable UUID id) {
        return venueService.getVenueById(id);
    }
}
