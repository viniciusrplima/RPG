package com.pacheco.game.component;

public class StatusComponent implements Component {

    public double maxHealth;
    public double health;

    public StatusComponent(double maxHealth, double health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

}
