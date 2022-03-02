package com.pacheco.game.component;

import com.pacheco.game.core.Position;

public class PositionComponent implements Component {
    private Position position;

    public PositionComponent(double x, double y) {
        position = new Position(x, y);
    }

    public void move(double x, double y) {
        position = position.add(x, y);
    }

    public void move(Position offset) {
        move(offset.x, offset.y);
    }

    public Position getPosition() {
        return position;
    }
}
