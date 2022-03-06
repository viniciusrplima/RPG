package com.pacheco.game;

import com.pacheco.game.util.SpriteUtil;
import javafx.scene.image.Image;

public class SpritesHolder {

    public static Image GRASS;
    public static Image WOOD_BLOCK;
    public static Image MUD;
    public static Image ROCK_BLOCK;

    public static void loadSprites() {
        GRASS = loadSprite("grass");
        WOOD_BLOCK = loadSprite("wood-block");
        MUD = loadSprite("mud");
        ROCK_BLOCK = loadSprite("rock-block");
    }

    private static Image loadSprite(String spriteName) {
        return SpriteUtil.loadSprite(Constants.TILES_PATH + spriteName + ".png");
    }
}
