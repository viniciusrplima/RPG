package com.pacheco.game.component;

import com.pacheco.game.core.Vector2d;

public class PositionComponent implements Component {
    public Vector2d position;

    public PositionComponent(double x, double y) {
        position = new Vector2d(x, y);
    }

    public void move(double x, double y) {
        position = position.add(x, y);
    }

    public void move(Vector2d offset) {
        move(offset.x, offset.y);
    }

}
