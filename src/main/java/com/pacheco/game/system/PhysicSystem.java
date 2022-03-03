package com.pacheco.game.system;

import com.pacheco.game.component.BoundingBoxComponent;
import com.pacheco.game.component.BoundingBoxType;
import com.pacheco.game.component.PositionComponent;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Position;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;

public class PhysicSystem {

    private EntityPool entityPool;

    public PhysicSystem(EntityPool entityPool) {
        this.entityPool = entityPool;
    }

    public void update(double elapsedSeconds) {
        for (Entity entity1 : entityPool.getEntities()) {
            if (entity1.containsComponent(BoundingBoxComponent.class)) {
                BoundingBoxComponent bbComponent1 = entity1.getComponent(BoundingBoxComponent.class);
                Box box1 = bbComponent1.box
                            .add(entity1.getComponent(PositionComponent.class).position);

                for (Entity entity2 : entityPool.getEntities()) {
                    if (entity2.containsComponent(BoundingBoxComponent.class)) {
                        BoundingBoxComponent bbComponent2 = entity2.getComponent(BoundingBoxComponent.class);
                        Box box2 = bbComponent2.box
                                .add(entity2.getComponent(PositionComponent.class).position);

                        if (box1.intersects(box2)) {
                            Position offset = calcOffset(box1, box2);
                            if (bbComponent1.type == BoundingBoxType.DYNAMIC)
                                entity1.getComponent(PositionComponent.class).move(offset);
                            if (bbComponent2.type == BoundingBoxType.DYNAMIC)
                                entity2.getComponent(PositionComponent.class).move(offset.multiply(-1));
                        }
                    }
                }
            }
        }
    }

    private Position calcOffset(Box box1, Box box2) {
        Position offset = new Position(0, 0);
        double dx = Math.min(box1.right, box2.right) - Math.max(box1.left, box2.left) + 1;
        double dy = Math.min(box1.bottom, box2.bottom) - Math.max(box1.top, box2.top) + 1;
        Position center1 = box1.center();
        Position center2 = box2.center();

        if (dx < dy) {
            if (center1.x > center2.x) offset = new Position(dx, 0);
            else offset = new Position(- dx, 0);
        }
        else {
            if (center1.y > center2.y) offset = new Position(0, dy / 2);
            else offset = new Position(0, - dy / 2);
        }
        return offset;
    }
}
