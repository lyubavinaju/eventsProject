package com.eventsproject.dao.counter;

import lombok.Getter;

@Getter
public class ByNameCounter extends Counter {
    private final String name;

    public ByNameCounter(String name, long count) {
        super(count);
        this.name = name;
    }

    @Override
    protected String toJsonStringImpl() {
        return "name: '" + name + '\'';
    }
}
