package com.pacheco.game.map;

import com.pacheco.game.SpritesHolder;
import com.pacheco.game.component.*;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;
import javafx.scene.paint.Color;

public class MapBuilder {

    private static final String mapString = "" +
            "xxxxx.........xxxxx.....xxxx\n" +
            "xxxxx.........xxxxx........x\n" +
            "x..........................x\n" +
            "x..........................x\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "x..........................\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    public void build(EntityPool entityPool) {
        int cellW = 50;
        int cellH = 50;
        double offsetW = -300;
        double offsetH = -300;
        int column = 0;
        int line = 0;
        for (int i = 0; i < mapString.length(); i++) {
            switch (mapString.charAt(i)) {
                case 'x': {
                    Entity wall = new Entity();
                    wall.setComponent(PositionComponent.class, new PositionComponent(cellW * column, cellH * line));
                    wall.setComponent(BoundingBoxComponent.class,
                            new BoundingBoxComponent(0, 0, cellW, cellH, BoundingBoxType.SOLID));
                    wall.setComponent(GraphicComponent.class, new TileGraphicComponent(SpritesHolder.WOOD_BLOCK, cellW, cellH));
                    entityPool.addEntity(wall);
                    break;
                }
                case '.': {
                    Entity grass = new Entity();
                    grass.setComponent(PositionComponent.class, new PositionComponent(cellW * column, cellH * line));
                    grass.setComponent(GraphicComponent.class, new TileGraphicComponent(SpritesHolder.GRASS, cellW, cellH));
                    entityPool.addEntity(grass);
                    break;
                }
                case '\n': {
                    line++;
                    column = -1;
                    break;
                }
            }
            column++;
        }
    }
}
