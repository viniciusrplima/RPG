package com.pacheco.game.core;


public class Vector2d {
    public double x;
    public double y;

    public Vector2d() {
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(double x, double y) {
        return new Vector2d(this.x + x, this.y + y);
    }

    public Vector2d subtract(double x, double y) {
        return new Vector2d(this.x - x, this.y - y);
    }

    public Vector2d multiply(double factor) {
        return new Vector2d(this.x * factor, this.y * factor);
    }

    public Vector2d add(Vector2d position) {
        return add(position.x, position.y);
    }

    public Vector2d subtract(Vector2d position) {
        return subtract(position.x, position.y);
    }

    public double distance(Vector2d position) {
        Vector2d diff = position.subtract(this);
        return Math.sqrt(diff.x * diff.x + diff.y * diff.y);
    }

    public Vector2d clone() {
        return new Vector2d(x, y);
    }
}
