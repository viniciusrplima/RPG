package com.pacheco.game.map;

import com.pacheco.game.component.*;
import com.pacheco.game.entity.Entity;
import javafx.scene.image.Image;

public class TileFactory {

    private double initialX;
    private double initialY;
    private double tileW;
    private double tileH;

    public TileFactory(double initialX, double initialY, double tileW, double tileH) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.tileW = tileW;
        this.tileH = tileH;
    }

    public Entity createSolid(Image sprite, int column, int line) {
        Entity solid = new Entity();
        solid.setComponent(PositionComponent.class, new PositionComponent(initialX + tileW * column, initialY + tileH * line));
        solid.setComponent(BoundingBoxComponent.class,
                new BoundingBoxComponent(0, 0, tileW, tileH, BoundingBoxType.SOLID));
        solid.setComponent(
                GraphicComponent.class, new TileGraphicComponent(sprite, tileW, tileH));
        return solid;
    }

    public Entity createSurface(Image sprite, int column, int line) {
        Entity surface = new Entity();
        surface.setComponent(PositionComponent.class, new PositionComponent(initialX + tileW * column, initialY + tileH * line));
        surface.setComponent(GraphicComponent.class, new TileGraphicComponent(sprite, tileW, tileH));
        return surface;
    }
}
