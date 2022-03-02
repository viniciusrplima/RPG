package com.pacheco.game.system;

import com.pacheco.game.component.BoundingBoxComponent;
import com.pacheco.game.component.GraphicComponent;
import com.pacheco.game.component.PositionComponent;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Position;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphicSystem {

    private EntityPool entityPool;

    public GraphicSystem(EntityPool entityPool) {
        this.entityPool = entityPool;
    }

    public void render(GraphicsContext gc) {
        gc.setTransform(1, 0, 0, 1, 0, 0);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (Entity entity : entityPool.getEntities()) {
            if (entity.containsComponent(GraphicComponent.class)) {
                if (entity.containsComponent(PositionComponent.class)) {
                    PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
                    Position position = positionComponent.getPosition();
                    gc.setTransform(1, 0, 0, 1, position.x, position.y);
                }
                entity.getComponent(GraphicComponent.class).render(gc);
            }
        };

        // render bounding box
        gc.setTransform(1, 0, 0, 1, 0, 0);
        for (Entity entity : entityPool.getEntities()) {
            if (entity.containsComponent(GraphicComponent.class)) {
                if (entity.containsComponent(PositionComponent.class) &&
                        entity.containsComponent(BoundingBoxComponent.class)) {

                    Box box = entity.getComponent(BoundingBoxComponent.class).getBox()
                            .add(entity.getComponent(PositionComponent.class).getPosition());
                    gc.setStroke(Color.GREEN);
                    gc.setLineWidth(2.0f);
                    gc.strokeRect(box.left, box.top, box.right - box.left, box.bottom - box.top);
                }
            }
        };
    }
}
