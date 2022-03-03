package com.pacheco.game.core;

public class Box {
    public double left;
    public double top;
    public double right;
    public double bottom;

    public Box() {
    }

    public Box(double left, double top, double right, double bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Box add(Position position) {
        return new Box(left + position.x, top + position.y,
                right + position.x, bottom + position.y);
    }

    public Boolean intersects(Box box) {
        return !(left > box.right || right < box.left || top > box.bottom || bottom < box.top);
    }

    public Position center() {
        return new Position((left + right) / 2, (top + bottom) / 2);
    }
}
