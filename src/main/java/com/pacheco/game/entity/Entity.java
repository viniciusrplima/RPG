package com.pacheco.game.entity;

import com.pacheco.game.component.Component;

import java.util.HashMap;
import java.util.Objects;

public class Entity {
    private static Long lasId = 1L;

    private Long id;
    private HashMap<Class<?>, Component> components;

    public Entity() {
        id = lasId++;
        components = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public Boolean containsComponent(Class<?> type) {
        return components.containsKey(type);
    }

    public <T> T getComponent(Class<T> type) {
        return (T) components.get(type);
    }

    public void setComponent(Class<?> type, Component component) {
        components.put(type, component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
