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

    public Box add(Vector2d position) {
        return new Box(left + position.x, top + position.y,
                right + position.x, bottom + position.y);
    }

    public Boolean intersects(Box box) {
        return !(left > box.right || right < box.left || top > box.bottom || bottom < box.top);
    }

    public Vector2d center() {
        return new Vector2d((left + right) / 2, (top + bottom) / 2);
    }

    public Box clone() {
        return new Box(left, top, right, bottom);
    }
}
