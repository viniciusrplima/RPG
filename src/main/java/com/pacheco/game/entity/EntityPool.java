package com.pacheco.game.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class EntityPool {
    private HashMap<Long, Entity> entities;

    public EntityPool() {
        entities = new HashMap<>();
    }

    public Collection<Entity> getEntities() {
        return entities.values().stream()
                .sorted(Comparator.comparingInt(Entity::getzIndex))
                .collect(Collectors.toList());
    }

    public Entity getEntity(Long id) {
        return entities.get(id);
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getId(), entity);
    }

    public void clear() {
        entities.clear();
    }
}
