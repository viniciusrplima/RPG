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
    }

    private Image sprite;
    private HashMap<String, Action> animationDefinition;

    public Animation(Image sprite,
                     HashMap<String, Action> animationDefinition) {
        this.sprite = sprite;
        this.animationDefinition = animationDefinition;
    }

    public void render(GraphicsContext gc, String actionName, double completion) {
        double scale = 3;
        Action action = animationDefinition.get(actionName);
        int step = ((int) Math.floor((action.definition.size() + 1) * completion)) % action.definition.size();
        Box actual = action.definition.get(step);

        gc.drawImage(sprite, actual.left, actual.top, actual.right, actual.bottom,
                0, 0, action.dimensions.x * scale, action.dimensions.y * scale);
    }

}
