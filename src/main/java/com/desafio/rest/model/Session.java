package com.desafio.rest.model;

import java.util.HashMap;
import java.util.Map;

public class Session {

    public static final Session instance = new Session();
    private final Map<String, Object> values = new HashMap<>();

    @SuppressWarnings("WeakerAccess")
    public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public void reset() {
        this.values.clear();
    }

}
