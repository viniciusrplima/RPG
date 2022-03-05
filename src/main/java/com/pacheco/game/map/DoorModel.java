package com.pacheco.game.map;

import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;

public class DoorModel {

    public String name;
    public Vector2d position;
    public Vector2d playerPosition;
    public Box box;
    public String destinationMap;
    public String destinationDoor;

    public DoorModel() {
    }
}
