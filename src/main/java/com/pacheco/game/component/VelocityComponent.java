package com.pacheco.game.component;

import com.pacheco.game.core.Vector2d;

public class VelocityComponent implements Component {

    public Vector2d velocity;

    public VelocityComponent() {
        velocity = new Vector2d(0, 0);
    }

    public void up(double x) {
        velocity = new Vector2d(0, -x);
    }

    public void down(double x) {
        velocity = new Vector2d(0, x);
    }

    public void left(double x) {
        velocity = new Vector2d(-x, 0);
    }

    public void right(double x) {
        velocity = new Vector2d(x, 0);
    }
}
