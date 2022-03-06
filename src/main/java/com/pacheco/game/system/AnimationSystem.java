package com.pacheco.game.system;

import com.pacheco.game.component.AnimationGraphicComponent;
import com.pacheco.game.component.GraphicComponent;
import com.pacheco.game.component.NPCStateComponent;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;

public class AnimationSystem {

    private EntityPool entityPool;

    public AnimationSystem(EntityPool entityPool) {
        this.entityPool = entityPool;
    }

    public void update(double elapsedSeconds) {
        for (Entity entity : entityPool.getEntitiesByComponent(NPCStateComponent.class)) {
            AnimationGraphicComponent animationGraphicComponent =
                    (AnimationGraphicComponent) entity.getComponent(GraphicComponent.class);
            NPCStateComponent npcStateComponent = entity.getComponent(NPCStateComponent.class);

            animationGraphicComponent.action = npcStateComponent.npcState.name();
            animationGraphicComponent.time += elapsedSeconds;
        }
    }
}
