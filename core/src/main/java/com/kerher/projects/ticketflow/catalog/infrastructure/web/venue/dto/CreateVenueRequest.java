package com.kerher.projects.ticketflow.catalog.infrastructure.web.venue.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateVenueRequest(
        @NotBlank(message = "name cannot be blank") String name,
        @NotBlank(message = "address cannot be blank") String address,
        @NotBlank(message = "city cannot be blank") String city) {}
