package com.kerher.projects.ticketflow.catalog.integration;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VenueIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createVenue_thenGetVenueById_returnsSameVenue() throws Exception {
        String body = """
                {"name": "Crocus City Hall", "address": "MKAD 66th km", "city": "Moscow"}
                """;

        MvcResult created = mockMvc.perform(post("/api/v1/venues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Crocus City Hall"))
                .andReturn();

        String id = JsonPath.read(created.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(get("/api/v1/venues/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.city").value("Moscow"));
    }

    @Test
    void getVenue_whenVenue_doesNotExist_thenThrowException() throws Exception {
        String venueId = UUID.randomUUID().toString();
        mockMvc.perform(get("/api/v1/venues/{id}", venueId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(String.format("Venue not found: %s", venueId)))
                .andReturn();
    }

    @Test
    void createVenue_whenVenueNameIsEmpty_thenThrowException() throws Exception {
        String body = """
                {"name": "", "address": "MKAD 66th km", "city": "Moscow"}
                """;

        mockMvc.perform(post("/api/v1/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("name cannot be blank"))
                .andReturn();
    }
}