package com.pacheco.game.entity;

import com.pacheco.game.component.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Entity {
    private static Long lastId = 1L;

    private Long id;
    private int zIndex;
    private HashMap<Class<?>, Component> components;

    public Entity() {
        this(0);
    }

    public Entity(int zIndex) {
        id = lastId++;
        components = new HashMap<>();
        this.zIndex = zIndex;
    }

    public Long getId() {
        return id;
    }

    public Collection<Class<?>> getComponentTypes() {
        return components.keySet();
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

    public int getzIndex() {
        return zIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
