package com.eventsproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private boolean isAuthorized;


    public Event(String name, LocalDate date, String ip, boolean isAuthorized) {
        this.name = name;
        this.date = date;
        this.ip = ip;
        this.isAuthorized = isAuthorized;
    }

}
