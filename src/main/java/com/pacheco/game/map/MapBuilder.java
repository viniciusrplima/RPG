package com.pacheco.game.map;

import com.pacheco.game.SpritesHolder;
import com.pacheco.game.entity.EntityPool;
import com.pacheco.game.util.ResourceUtil;

public class MapBuilder {

    private TileFactory tileFactory;

    public MapBuilder() {
        int cellW = 50;
        int cellH = 50;
        double offsetW = -300;
        double offsetH = -300;
        tileFactory = new TileFactory(offsetW, offsetH, cellW, cellH);
    }

    public void buildFromFile(EntityPool entityPool, String mapfile) {
        String mapString = ResourceUtil.getContentFromResource(mapfile);
        build(entityPool, mapString);
    }

    public void build(EntityPool entityPool, String mapString) {
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
