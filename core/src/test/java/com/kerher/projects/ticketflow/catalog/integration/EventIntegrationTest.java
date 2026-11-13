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
public class EventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String createVenue() throws Exception {
        String venueBody = """
                {"name": "TestName", "address": "TestAddress", "city": "TestCity"}
                """;

        MvcResult createdVenue = mockMvc.perform(post("/api/v1/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(venueBody))
                .andExpect(status().isOk())
                .andReturn();

        return JsonPath.read(createdVenue.getResponse().getContentAsString(), "$.id");
    }

    @Test
    void createEvent_thenGetEventById_returnsSameEvent() throws Exception {

        String venueId = createVenue();

        String eventBody = String.format(
                """
                {"title": "Концерт Доры", "description": "Легендарный, знаменитый концерт для мальчиков",
                "venueId": "%s", "category": "CONCERT"}
                """, venueId);

        MvcResult createdEvent = mockMvc.perform(post("/api/v1/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(eventBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.category").value("CONCERT"))
                .andReturn();

        String id = JsonPath.read(createdEvent.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(get("/api/v1/events/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.venueId").value(venueId))
                .andExpect(jsonPath("$.title").value("Концерт Доры"));
    }

    @Test
    void createEvent_whenVenueId_doesNotExist_thenThrowException() throws Exception {

        String venueId = UUID.randomUUID().toString();

        String eventBody = String.format(
                """
                {"title": "Концерт Доры", "description": "Легендарный, знаменитый концерт для мальчиков",
                "venueId": "%s", "category": "CONCERT"}
                """, venueId);

        mockMvc.perform(post("/api/v1/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(eventBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(String.format("Venue not found: %s",  venueId)));
    }

    @Test
    void createEvent_whenEventTitle_isNotValid_thenThrowException() throws Exception {
        String eventBody = String.format(
                """
                {"title": "", "description": "Легендарный, знаменитый концерт для мальчиков",
                "venueId": "%s", "category": "CONCERT"}
                """, UUID.randomUUID());

        mockMvc.perform(post("/api/v1/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(eventBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("title cannot be blank"));

    }

    @Test
    void createEvent_whenEventCategory_isWrong_thenThrowException() throws Exception {
        String eventBody = String.format(
                """
                {"title": "wrongwrong", "description": "Легендарный, знаменитый концерт для мальчиков",
                "venueId": "%s"}
                """, UUID.randomUUID());

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.category").value("category is required"));

    }



    //todo тест со сценарием, где не сущестует переданного venueId

    //todo тест со сценарием, где данные не валидны(не уверен что надо его писать здесь,
    // карчое спросить у клода нужно ли делать такой тест, а если и нужно,
    // то с здесь в интеграционном или лучше в юнит тесте потом(в самом юнит тесте контроллера))

}
