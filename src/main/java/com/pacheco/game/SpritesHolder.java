package com.pacheco.game;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpritesHolder {

    public static Image GRASS;
    public static Image WOOD_BLOCK;
    public static Image MUD;
    public static Image ROCK_BLOCK;

    public static void loadSprites() {
        GRASS = loadSprite("src/main/resources/sprites/grass.png");
        WOOD_BLOCK = loadSprite("src/main/resources/sprites/wood-block.png");
        MUD = loadSprite("src/main/resources/sprites/mud.png");
        ROCK_BLOCK = loadSprite("src/main/resources/sprites/rock-block.png");
    }

    private static Image loadSprite(String filename) {
        try {
            FileInputStream stream = new FileInputStream(filename);
            return new Image(stream);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error opening image file: " + fnfe.getMessage());
        }
        return null;
    }
}
