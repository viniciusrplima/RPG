package com.pacheco.game.component;

import com.pacheco.game.core.Box;

public class BoundingBoxComponent implements Component {
    public Box box;
    public BoundingBoxType type;

    public BoundingBoxComponent(double left, double top, double right, double bottom) {
        this(left, top, right, bottom, BoundingBoxType.DYNAMIC);
    }

    public BoundingBoxComponent(double left, double top, double right, double bottom, BoundingBoxType type) {
        box = new Box(left, top, right, bottom);
        this.type = type;
    }
}
