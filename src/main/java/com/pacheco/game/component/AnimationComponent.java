package com.pacheco.game.component;

import com.pacheco.game.animation.Animation;
import com.pacheco.game.animation.AnimationLoader;
import javafx.scene.canvas.GraphicsContext;

public class AnimationComponent implements GraphicComponent {

    Animation animation;
    double time;

    public AnimationComponent(String animationName) {
        this.time = 0.0f;
        this.animation = AnimationLoader.load(animationName);
    }

    @Override
    public void render(GraphicsContext gc) {
        time += 0.003f;
        animation.render(gc, "WALK_DOWN", time);
    }
}
