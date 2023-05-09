package com.eventsproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 50)
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String pwdHash;
    @Transient
    private String pwd;

    public Person(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }
}
