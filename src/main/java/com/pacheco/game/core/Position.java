package com.pacheco.game.core;


public class Position {
    public double x;
    public double y;

    public Position() {
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position add(double x, double y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position subtract(double x, double y) {
        return new Position(this.x - x, this.y - y);
    }

    public Position multiply(double factor) {
        return new Position(this.x * factor, this.y * factor);
    }

    public Position add(Position position) {
        return add(position.x, position.y);
    }

    public Position subtract(Position position) {
        return subtract(position.x, position.y);
    }

    public Position clone() {
        return new Position(x, y);
    }
}
