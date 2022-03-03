package com.pacheco.game.map;

import com.pacheco.game.SpritesHolder;
import com.pacheco.game.entity.EntityPool;

public class MapBuilder {

    private static final String mapString = "" +
            "wwwww.........wwwww.....wwww\n" +
            "wwwww.........wwwww........w\n" +
            "w..........................w\n" +
            "w..................r.......w\n" +
            "w.....r....................\n" +
            "w..........................\n" +
            "w..........................\n" +
            "w.........wwww.............\n" +
            "w..........................\n" +
            "w....rr....................\n" +
            "w..........................\n" +
            "w.........rrrrr............\n" +
            "w..........................\n" +
            "w..........................\n" +
            "w.....r............r.......\n" +
            "w..........................\n" +
            "w..........................\n" +
            "wwwwwwwwwwwwwwwwwwwwwwwwwww";

    private TileFactory tileFactory;

    public MapBuilder() {
        int cellW = 50;
        int cellH = 50;
        double offsetW = -300;
        double offsetH = -300;
        tileFactory = new TileFactory(offsetW, offsetH, cellW, cellH);
    }

    public void build(EntityPool entityPool) {
        int column = 0;
        int line = 0;
        for (int i = 0; i < mapString.length(); i++) {
            char c = mapString.charAt(i);
            if (c == 'w') entityPool.addEntity(tileFactory.createSolid(SpritesHolder.WOOD_BLOCK, column, line));
            if (c == 'r') entityPool.addEntity(tileFactory.createSolid(SpritesHolder.ROCK_BLOCK, column, line));
            if (c == '.') entityPool.addEntity(tileFactory.createSurface(SpritesHolder.GRASS, column, line));

            if (c == '\n') {
                line++;
                column = 0;
            } else {
                column++;
            }
        }
    }
}
