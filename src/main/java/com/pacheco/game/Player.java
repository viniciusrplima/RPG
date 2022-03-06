package com.pacheco.game;

import com.pacheco.game.component.*;
import com.pacheco.game.entity.Entity;

public class Player {

    private Entity entity;

    public Player() {
        entity = new Entity(9999999);
        entity.setComponent(GraphicComponent.class, new NPCAnimationGraphicComponent("knight", 3.0f));
        entity.setComponent(PositionComponent.class, new PositionComponent(250, 250));
        entity.setComponent(BoundingBoxComponent.class, new BoundingBoxComponent(0, 0, 90, 90));
        entity.setComponent(VelocityComponent.class, new VelocityComponent());
        entity.setComponent(StatusComponent.class, new StatusComponent(100, 85));
    }

    public void up(double speed) {
        entity.getComponent(VelocityComponent.class).up(Constants.PLAYER_SPEED);
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_UP);
    }

    public void down(double speed) {
        entity.getComponent(VelocityComponent.class).down(Constants.PLAYER_SPEED);
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_DOWN);
    }

    public void left(double speed) {
        entity.getComponent(VelocityComponent.class).left(Constants.PLAYER_SPEED);
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_LEFT);
    }

    public void right(double speed) {
        entity.getComponent(VelocityComponent.class).right(Constants.PLAYER_SPEED);
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_RIGHT);
    }

    public void stop() {
        entity.getComponent(VelocityComponent.class).stop();
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_STOP);
    }

    public void attack() {
        entity.getComponent(NPCAnimationGraphicComponent.class)
                .enterTransition(NPCAnimationGraphicComponent.NPCTransition.T_ATTACK);
    }

    public Entity getEntity() {
        return entity;
    }
}
