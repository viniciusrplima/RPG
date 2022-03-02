package com.pacheco.game.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TileGraphicComponent implements GraphicComponent {

    public Image sprite;
    public double width;
    public double height;

    public TileGraphicComponent(Image sprite, double width, double height) {
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite, 0, 0, width, height);
    }
}
