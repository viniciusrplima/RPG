package com.pacheco.game.component;

import static com.pacheco.game.component.NPCStateComponent.NPCTransition.*;
import static com.pacheco.game.component.NPCStateComponent.NPCState.*;

public class NPCStateComponent implements Component {

    public enum NPCState {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        WALK_UP,
        WALK_DOWN,
        WALK_LEFT,
        WALK_RIGHT
    }

    public enum NPCTransition {
        T_UP,
        T_DOWN,
        T_LEFT,
        T_RIGHT,
        T_STOP
    }

    public NPCState npcState;

    public NPCStateComponent() {
        this.npcState = DOWN;
    }

    public void enterTransition(NPCTransition npcTransition) {
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
    }
}
