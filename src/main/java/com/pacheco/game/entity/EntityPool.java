package com.pacheco.game.entity;

import com.pacheco.game.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityPool {
    private HashMap<Long, Entity> entities;

    public EntityPool() {
        entities = new HashMap<>();
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.values());
    }

    public Entity getEntity(Long id) {
        return entities.get(id);
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getId(), entity);
    }
}
