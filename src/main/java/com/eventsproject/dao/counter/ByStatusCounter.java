package com.eventsproject.dao.counter;

import lombok.Getter;

@Getter
public class ByStatusCounter extends Counter {
    private final boolean status;

    public ByStatusCounter(boolean status, long count) {
        super(count);
        this.status = status;
    }

    @Override
    protected String toJsonStringImpl() {
        return "status: " + status;
    }
}
