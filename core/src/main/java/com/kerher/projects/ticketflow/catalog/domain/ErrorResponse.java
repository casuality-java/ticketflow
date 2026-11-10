package com.kerher.projects.ticketflow.catalog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
public class ErrorResponse {

    private final HttpStatus status;

    private final String message;

    private final String path;

    private final LocalDateTime timestamp;
}
