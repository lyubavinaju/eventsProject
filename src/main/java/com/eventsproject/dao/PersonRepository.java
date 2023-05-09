package com.eventsproject.dao;

import com.eventsproject.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Person person) {
        entityManager.createNativeQuery("INSERT INTO person (username, pwd_hash) VALUES (?, crypt(?, gen_salt('md5')))")
                .setParameter(1, person.getUsername())
                .setParameter(2, person.getPwd())
                .executeUpdate();
    }

    public Person findByCredentials(Person person) {
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p where p.username = :username and p.pwdHash = crypt(:pwd, p.pwdHash)", Person.class);
        Optional<Person> res = query.setParameter("username", person.getUsername()).setParameter("pwd", person.getPwd())
                .getResultStream().findAny();
        return res.orElse(null);
    }
}
