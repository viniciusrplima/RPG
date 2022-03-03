package com.pacheco.game.component;

import com.pacheco.game.core.Box;
import com.pacheco.game.map.DoorModel;

public class DoorComponent implements Component {

    public Box boundingBox;
    public DoorModel doorModel;

    public DoorComponent(Box boundingBox, DoorModel doorModel) {
        this.boundingBox = boundingBox;
        this.doorModel = doorModel;
    }
}
