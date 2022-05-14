package com.pacheco.game.entity;

import java.util.*;
import java.util.stream.Collectors;

public class EntityPool {
    private HashMap<Long, Entity> entities;
    private HashMap<Class<?>, List<Entity>> classEntities;

    public EntityPool() {
        entities = new HashMap<>();
        classEntities = new HashMap<>();
    }

    public Collection<Entity> getEntities() {
        return entities.values().stream()
                .sorted(Comparator.comparingInt(Entity::getzIndex))
                .collect(Collectors.toList());
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getId(), entity);
        for (Class<?> type : entity.getComponentTypes()) {
            if (!classEntities.containsKey(type)) {
                classEntities.put(type, new ArrayList<>());
            }
            classEntities.get(type).add(entity);
        }
    }

    public List<Entity> getEntitiesByComponent(Class<?> componentType) {
        if (classEntities.containsKey(componentType)) {
            return classEntities.get(componentType);
        } else {
            return List.of();
        }
    }

    public void clear() {
        entities.clear();
        classEntities.clear();
    }
}
