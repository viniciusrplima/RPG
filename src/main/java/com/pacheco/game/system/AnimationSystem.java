package com.pacheco.game.system;

import com.pacheco.game.component.NPCAnimationGraphicComponent;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;

public class AnimationSystem {

    private EntityPool entityPool;

    public AnimationSystem(EntityPool entityPool) {
        this.entityPool = entityPool;
    }

    public void update(double elapsedSeconds) {
        for (Entity entity : entityPool.getEntitiesByComponent(NPCAnimationGraphicComponent.class)) {
            NPCAnimationGraphicComponent animationGraphicComponent =
                    entity.getComponent(NPCAnimationGraphicComponent.class);

            animationGraphicComponent.update(elapsedSeconds);
        }
    }
}
