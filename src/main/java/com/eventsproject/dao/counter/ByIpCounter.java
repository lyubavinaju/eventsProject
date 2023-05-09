package com.eventsproject.dao.counter;

import lombok.Getter;

@Getter
public class ByIpCounter extends Counter {
    private final String ip;

    public ByIpCounter(String ip, long count) {
        super(count);
        this.ip = ip;
    }

    @Override
    protected String toJsonStringImpl() {
        return "ip: '" + ip + '\'';
    }
}
