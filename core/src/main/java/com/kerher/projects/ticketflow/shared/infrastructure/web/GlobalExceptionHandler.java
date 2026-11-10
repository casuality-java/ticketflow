package com.kerher.projects.ticketflow.shared.infrastructure.web;

import com.kerher.projects.ticketflow.catalog.domain.ErrorResponse;
import com.kerher.projects.ticketflow.catalog.domain.VenueNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorResponse> handleVenueNotFoundException(VenueNotFoundException ex,
                                                                               HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
