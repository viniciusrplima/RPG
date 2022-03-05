package com.pacheco.game;

import com.pacheco.game.component.*;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;
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

import java.util.HashSet;

public class Game {

    private Canvas canvas;
    private HashSet<KeyCode> keyboard;

    private EntityPool entityPool;
    private GraphicSystem graphicSystem;
    private PhysicSystem physicSystem;
    private MapSystem mapSystem;

    private Entity player;

    public Game(Canvas canvas) {
        this.canvas = canvas;
        this.keyboard = new HashSet<>();
    }

    public void start() {
        SpritesHolder.loadSprites();

        entityPool = new EntityPool();

        graphicSystem = new GraphicSystem(entityPool);
        physicSystem = new PhysicSystem(entityPool);

        player = new Entity(9999999);
        player.setComponent(GraphicComponent.class, new BoxGraphicComponent(Color.RED, 50, 50));
        player.setComponent(PositionComponent.class, new PositionComponent(250, 250));
        player.setComponent(BoundingBoxComponent.class, new BoundingBoxComponent(0, 0, 50, 50));
        player.setComponent(VelocityComponent.class, new VelocityComponent());
        player.setComponent(StatusComponent.class, new StatusComponent(100, 85));

        mapSystem = new MapSystem(entityPool, player);

        entityPool.addEntity(player);
    }

    public void update(double elapsedSeconds) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        updateInput();

        physicSystem.update(elapsedSeconds);
        mapSystem.update();

        Vector2d playerPos = player.getComponent(PositionComponent.class).position;
        Box playerBB = player.getComponent(BoundingBoxComponent.class).box;

        Transform transform = new Transform();
        transform.translate(playerPos.multiply(-1));
        transform.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        graphicSystem.render(gc, transform);

        // render player status
        new Transform().apply(gc);
        StatusComponent statusComponent = player.getComponent(StatusComponent.class);
        double maxHealthBarWidth = 300;
        double healthBarWidth = maxHealthBarWidth * statusComponent.health / statusComponent.maxHealth;
        
        gc.setLineWidth(10);
        gc.setStroke(Color.RED);
        gc.strokeLine(20, 20, maxHealthBarWidth + 20, 20);
        gc.setStroke(Color.color(0.4f, 1.0f, 0.3f));
        gc.strokeLine(20, 20, healthBarWidth + 20, 20);
    }

    public void updateInput() {
        if (isKeyPressed(KeyCode.W)) player.getComponent(VelocityComponent.class).up(Constants.PLAYER_SPEED);
        else if (isKeyPressed(KeyCode.S)) player.getComponent(VelocityComponent.class).down(Constants.PLAYER_SPEED);
        else if (isKeyPressed(KeyCode.A)) player.getComponent(VelocityComponent.class).left(Constants.PLAYER_SPEED);
        else if (isKeyPressed(KeyCode.D)) player.getComponent(VelocityComponent.class).right(Constants.PLAYER_SPEED);
        else player.getComponent(VelocityComponent.class).stop();
    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keyboard.contains(keyCode);
    }

    public void keyboardInput(KeyEvent key) {
        if (key.getEventType() == KeyEvent.KEY_PRESSED) {
            keyboard.add(key.getCode());
        }
        if (key.getEventType() == KeyEvent.KEY_RELEASED) {
            keyboard.remove(key.getCode());
        }
    }
}
