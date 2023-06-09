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
    /**
     * Hash calculated from raw password, stored in database
     */
    @Column
    private String passwordHash;
    /**
     * Raw password doesn't store in database
     */
    @Transient
    private String password;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
