package com.pacheco.game.component;

import com.pacheco.game.animation.Animation;
import com.pacheco.game.animation.AnimationLoader;
import javafx.scene.canvas.GraphicsContext;

public class AnimationGraphicComponent implements GraphicComponent {

    public Animation animation;
    public String action;
    public double time;

    public AnimationGraphicComponent(String animationName) {
        this.time = 0.0f;
        this.action = NPCStateComponent.NPCState.DOWN.name();
        this.animation = AnimationLoader.load(animationName);
    }

    @Override
    public void render(GraphicsContext gc) {
        animation.render(gc, action, time);
    }
}
