package com.pacheco.game.component;

import com.pacheco.game.core.Vector2d;
import com.pacheco.game.entity.Entity;

import static com.pacheco.game.component.AIComponent.AITransition.*;
import static com.pacheco.game.component.AIComponent.AIState.*;

public class AIComponent implements Component {

    public enum AIState {
        IDLE,
        SEEKING,
        ATTACKING
    }

    public enum AITransition {
        T_NO_ENEMY,
        T_ENEMY_FAR,
        T_ENEMY_NEAR
    }

    public AIState aiState;

    public void enterTransition(AITransition aiTransition) {
        if (aiTransition == T_ENEMY_FAR) aiState = IDLE;
        if (aiTransition == T_ENEMY_NEAR) aiState = ATTACKING;
        if (aiTransition == T_NO_ENEMY) aiState = SEEKING;
    }

    public void update(Entity npc, Entity player) {
        Vector2d npcPosition = npc.getComponent(PositionComponent.class).position;
        Vector2d playerPosition = player.getComponent(PositionComponent.class).position;
        double distance = Math.abs(npcPosition.distance(playerPosition));

        if (distance < 100) enterTransition(T_ENEMY_NEAR);
        else if (distance < 300) enterTransition(T_ENEMY_FAR);
        else enterTransition(T_NO_ENEMY);
    }
}
