package com.kerher.projects.ticketflow.catalog.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Venue {

    private UUID id;

    private String name;

    private String address;

    private String city;

}
