package com.pacheco.game.util;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpriteUtil {

    public static Image loadSprite(String filename) {
        try {
            FileInputStream stream = new FileInputStream(filename);
            return new Image(stream);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error opening image file: " + fnfe.getMessage());
        }
        return null;
    }

}
