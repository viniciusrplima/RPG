package com.pacheco.game.map;

import com.pacheco.game.core.Vector2d;

import java.util.List;

public class MapModel {

    public Vector2d playerInitialPos;
    public List<DoorModel> doors;
    public List<NPCModel> npcs;

    public MapModel() {
    }
}
