package com.pacheco.game.component;

import com.pacheco.game.animation.Animation;
import com.pacheco.game.animation.AnimationLoader;
import javafx.scene.canvas.GraphicsContext;

import static com.pacheco.game.component.NPCAnimationGraphicComponent.NPCState.*;
import static com.pacheco.game.component.NPCAnimationGraphicComponent.NPCTransition.*;

public class NPCAnimationGraphicComponent implements GraphicComponent {

    public enum NPCState {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        WALK_UP,
        WALK_DOWN,
        WALK_LEFT,
        WALK_RIGHT,
        ATTACK_DOWN,
        ATTACK_UP,
        ATTACK_RIGHT,
        ATTACK_LEFT
    }

    public enum NPCTransition {
        T_UP,
        T_DOWN,
        T_LEFT,
        T_RIGHT,
        T_STOP,
        T_ATTACK,
        T_TIMEOUT
    }

    public Animation animation;
    public NPCState npcState;
    public double timeout;

    public NPCAnimationGraphicComponent(String animationName, double scale) {
        this.timeout = -1;
        this.npcState = DOWN;
        this.animation = AnimationLoader.load(animationName);
        this.animation.setScale(scale);
    }

    public void enterTransition(NPCTransition npcTransition) {
        // if has a timeout animation wait until animation ends
        if (timeout == -1) {
            if (npcTransition == T_UP) npcState = WALK_UP;
            if (npcTransition == T_DOWN) npcState = WALK_DOWN;
            if (npcTransition == T_LEFT) npcState = WALK_LEFT;
            if (npcTransition == T_RIGHT) npcState = WALK_RIGHT;
            if (npcTransition == T_STOP) {
                if (npcState == WALK_UP) npcState = UP;
                if (npcState == WALK_DOWN) npcState = DOWN;
                if (npcState == WALK_LEFT) npcState = LEFT;
                if (npcState == WALK_RIGHT) npcState = RIGHT;
            }
            if (npcTransition == T_ATTACK) {
                if (npcState == WALK_UP || npcState == UP) npcState = ATTACK_UP;
                if (npcState == WALK_DOWN || npcState == DOWN) npcState = ATTACK_DOWN;
                if (npcState == WALK_LEFT || npcState == LEFT) npcState = ATTACK_LEFT;
                if (npcState == WALK_RIGHT || npcState == RIGHT) npcState = ATTACK_RIGHT;
                timeout = animation.getTimeout(npcState.name());
            }
        }
        if (npcTransition == T_TIMEOUT) {
            timeout = -1;
            if (npcState == ATTACK_DOWN) npcState = DOWN;
            if (npcState == ATTACK_UP) npcState = UP;
            if (npcState == ATTACK_LEFT) npcState = LEFT;
            if (npcState == ATTACK_RIGHT) npcState = RIGHT;
        }
    }

    public void update(double elapsedSeconds) {
        animation.update(elapsedSeconds);
        if (timeout != -1) {
            timeout -= elapsedSeconds;
            if (timeout <= 0) {
                enterTransition(T_TIMEOUT);
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        animation.render(gc, npcState.name());
    }
}
