package com.eventsproject.controller;


import com.eventsproject.dao.EventRepository;
import com.eventsproject.dao.PersonRepository;
import com.eventsproject.dao.counter.Counter;
import com.eventsproject.model.Event;
import com.eventsproject.model.Person;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    @Autowired
    public EventController(EventRepository eventRepository, PersonRepository personRepository) {
        this.eventRepository = eventRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("event/save")
    public String save(@RequestParam String name, @RequestParam(required = false) String username,
                       @RequestParam(required = false) String pwd, HttpServletRequest request) {
        try {
            Person person = new Person(username, pwd);
            boolean auth = username != null && pwd != null && personRepository.findByCredentials(person) != null;
            Event event = new Event(name, LocalDate.now(), request.getRemoteAddr(), auth);
            eventRepository.save(event);
            return "Event saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "Something wrong: " + e.getMessage();
        }
    }

    @GetMapping("event/stats")
    public String getStatistics(@RequestParam LocalDate date, @RequestParam String name) {
        return List.of(eventRepository.countByName(date, name).stream().map(Counter::toJsonString).toList(),
                eventRepository.countByIp(date, name).stream().map(Counter::toJsonString).toList(),
                eventRepository.countByStatus(date, name).stream().map(Counter::toJsonString).toList()
        ).toString();
    }
}
