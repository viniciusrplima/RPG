package com.pacheco.game.system;

import com.pacheco.game.component.AIComponent;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;

public class AISystem {

    private EntityPool entityPool;
    private Entity player;

    public AISystem(EntityPool entityPool, Entity player) {
        this.entityPool = entityPool;
        this.player = player;
    }

    public void update() {
        for (Entity entity : entityPool.getEntitiesByComponent(AIComponent.class)) {
            AIComponent aiComponent = entity.getComponent(AIComponent.class);
            aiComponent.update(entity, player);
        }
    }
}
