package com.pacheco.game;

import com.pacheco.game.component.*;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Position;
import com.pacheco.game.core.Transform;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;
import com.pacheco.game.system.GraphicSystem;
import com.pacheco.game.system.MapSystem;
import com.pacheco.game.system.PhysicSystem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Game {

    private Canvas canvas;

    private EntityPool entityPool;
    private GraphicSystem graphicSystem;
    private PhysicSystem physicSystem;
    private MapSystem mapSystem;

    private Entity player;

    public Game(Canvas canvas) {
        this.canvas = canvas;
    }

    public void start() {
        SpritesHolder.loadSprites();

        entityPool = new EntityPool();

        graphicSystem = new GraphicSystem(entityPool);
        physicSystem = new PhysicSystem(entityPool);

        player = new Entity(9999999);
        player.setComponent(GraphicComponent.class, new BoxGraphicComponent(Color.RED, 100, 100));
        player.setComponent(PositionComponent.class, new PositionComponent(250, 250));
        player.setComponent(BoundingBoxComponent.class, new BoundingBoxComponent(0, 0, 100, 100));

        mapSystem = new MapSystem(entityPool, player);

        entityPool.addEntity(player);
    }

    public void update(double elapsedSeconds) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        physicSystem.update(elapsedSeconds);
        mapSystem.update();

        Position playerPos = player.getComponent(PositionComponent.class).position;
        Box playerBB = player.getComponent(BoundingBoxComponent.class).box;

        Transform transform = new Transform();
        transform.translate(playerPos.multiply(-1));
        transform.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        graphicSystem.render(gc, transform);

        // print player status
        new Transform().apply(gc);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0f);
        gc.strokeText(String.format("Player Position: %.2f, %.2f", playerPos.x, playerPos.y), 10, 20);
        gc.strokeText(String.format("Player BB: %.2f, %.2f, %.2f, %.2f",
                playerBB.left, playerBB.top, playerBB.right, playerBB.bottom), 10, 40);
    }

    public void keyboardInput(KeyEvent key) {
        if (key.getCode() == KeyCode.W) player.getComponent(PositionComponent.class).move(0, -25);
        if (key.getCode() == KeyCode.S) player.getComponent(PositionComponent.class).move(0, 25);
        if (key.getCode() == KeyCode.A) player.getComponent(PositionComponent.class).move(-25, 0);
        if (key.getCode() == KeyCode.D) player.getComponent(PositionComponent.class).move(25, 0);
    }
}
