package com.eventsproject;

import com.eventsproject.dao.EventRepository;
import com.eventsproject.model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
    @Autowired
    private EventRepository repository;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Checks that event saved with good input parameters
     * @throws Exception
     */
    @Test
    public void saveEventTest() throws Exception {
        List<Event> events = repository.findAll();
        mockMvc.perform(post("/event/save").queryParam("name", "eventName1"))
                .andExpect(status().isOk());
        Assertions.assertEquals(events.size() + 1, repository.findAll().size());
    }

    /**
     * Checks that event saved with authorization
     * @throws Exception
     */
    @Test
    public void saveEventWithAuthTest() throws Exception {
        List<Event> events = repository.findAll();
        mockMvc.perform(post("/event/save")
                        .queryParam("name", "eventName1")
                        .queryParam("username", "un111111")
                        .queryParam("password", "pass1"))
                .andExpect(status().isOk());
        Assertions.assertEquals(events.size() + 1, repository.findAll().size());
    }

    /**
     * Checks that event doesn't save with no input parameters
     * @throws Exception
     */
    @Test
    public void saveBadEvent() throws Exception {
        List<Event> events = repository.findAll();
        mockMvc.perform(post("/event/save"))
                .andExpect(status().isBadRequest());
        Assertions.assertEquals(events.size(), repository.findAll().size());
    }
}
