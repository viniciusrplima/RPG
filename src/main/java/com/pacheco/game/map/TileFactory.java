package com.pacheco.game.map;

import com.pacheco.game.component.*;
import com.pacheco.game.entity.Entity;
import javafx.scene.image.Image;

public class TileFactory {

    private double tileW;
    private double tileH;

    public TileFactory(double tileW, double tileH) {
        this.tileW = tileW;
        this.tileH = tileH;
    }

    public Entity createSolid(Image sprite, int column, int line) {
        Entity solid = new Entity();
        solid.setComponent(PositionComponent.class, new PositionComponent(tileW * column, tileH * line));
        solid.setComponent(BoundingBoxComponent.class,
                new BoundingBoxComponent(0, 0, tileW, tileH, BoundingBoxType.SOLID));
        solid.setComponent(
                GraphicComponent.class, new TileGraphicComponent(sprite, tileW, tileH));
        return solid;
    }

    public Entity createSurface(Image sprite, int column, int line) {
        Entity surface = new Entity();
        surface.setComponent(PositionComponent.class, new PositionComponent(tileW * column, tileH * line));
        surface.setComponent(GraphicComponent.class, new TileGraphicComponent(sprite, tileW, tileH));
        return surface;
    }
}
