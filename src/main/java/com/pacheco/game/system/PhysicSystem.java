package com.pacheco.game.system;

import com.pacheco.game.component.BoundingBoxComponent;
import com.pacheco.game.component.PositionComponent;
import com.pacheco.game.component.VelocityComponent;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;


public class PhysicSystem {

    private EntityPool entityPool;

    public PhysicSystem(EntityPool entityPool) {
        this.entityPool = entityPool;
    }

    public void update(double elapsedSeconds) {
        for (Entity entity1 : entityPool.getEntitiesByComponent(VelocityComponent.class)) {
            Vector2d velocity = entity1.getComponent(VelocityComponent.class).velocity;
            Vector2d newPosition = entity1.getComponent(PositionComponent.class).position.add(velocity.multiply(elapsedSeconds));

            BoundingBoxComponent bbComponent1 = entity1.getComponent(BoundingBoxComponent.class);
            Box box1 = bbComponent1.box.add(newPosition);

            boolean collide = false;
            for (Entity entity2 : entityPool.getEntitiesByComponent(BoundingBoxComponent.class)) {
                if (entity1.equals(entity2)) continue;

                BoundingBoxComponent bbComponent2 = entity2.getComponent(BoundingBoxComponent.class);
                Box box2 = bbComponent2.box
                        .add(entity2.getComponent(PositionComponent.class).position);

                if (box1.intersects(box2)) {
                    collide = true;
                }
            }

            if (!collide) {
                entity1.getComponent(PositionComponent.class).position = newPosition;
            }
        }
    }

    private Vector2d calcOffset(Box box1, Box box2) {
        Vector2d offset = new Vector2d(0, 0);
        double dx = Math.min(box1.right, box2.right) - Math.max(box1.left, box2.left) + 1;
        double dy = Math.min(box1.bottom, box2.bottom) - Math.max(box1.top, box2.top) + 1;
        Vector2d center1 = box1.center();
        Vector2d center2 = box2.center();

        if (dx < dy) {
            if (center1.x > center2.x) offset = new Vector2d(dx, 0);
            else offset = new Vector2d(- dx, 0);
        }
        else {
            if (center1.y > center2.y) offset = new Vector2d(0, dy / 2);
            else offset = new Vector2d(0, - dy / 2);
        }
        return offset;
    }
}
