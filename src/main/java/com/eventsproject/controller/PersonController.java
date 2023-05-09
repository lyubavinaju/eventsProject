package com.eventsproject.controller;

import com.eventsproject.dao.PersonRepository;
import com.eventsproject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Register new user
     *
     * @param username Username
     * @param password      Password
     * @return String with feedback
     */
    @PostMapping("user/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        try {
            Person person = new Person(username, password);
            personRepository.insertWithQuery(person);
            return "User saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "Something wrong: " + e.getMessage();
        }
    }
}
