package com.eventsproject.dao.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Counter {
    private final long count;

    public String toJsonString() {
        return "{" +
                toJsonStringImpl() + ", " +
                "count: " + count +
                '}';
    }

    protected abstract String toJsonStringImpl();
}
