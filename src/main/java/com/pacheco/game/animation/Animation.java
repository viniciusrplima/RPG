package com.pacheco.game.animation;

import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.List;

public class Animation {

    public static class Action {
        public Vector2d dimensions;
        public List<Box> definition;
        public double timeout;
    }

    private Image sprite;
    private HashMap<String, Action> animationDefinition;
    private double scale;
    private String actionName;
    private double timeInSeconds;

    public Animation(Image sprite,
                     HashMap<String, Action> animationDefinition) {
        this.sprite = sprite;
        this.scale = 1.0f;
        this.animationDefinition = animationDefinition;
    }

    public double getTimeout(String actionName) {
        return animationDefinition.get(actionName).timeout;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setAction(String actionName) {
        this.actionName = actionName;
        this.timeInSeconds = 0;
    }

    public void update(double elapsedSeconds) {
        this.timeInSeconds += elapsedSeconds;
    }

    public void render(GraphicsContext gc, String actionName) {
        Action action = animationDefinition.get(actionName);
        double completion = (timeInSeconds % action.timeout) / action.timeout;
        int step = ((int) Math.floor((action.definition.size() + 1) * completion)) % action.definition.size();
        Box actual = action.definition.get(step);

        gc.drawImage(sprite, actual.left, actual.top, actual.right, actual.bottom,
                0, 0, action.dimensions.x * scale, action.dimensions.y * scale);
    }

}
