package com.pacheco.game.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pacheco.game.SpritesHolder;
import com.pacheco.game.component.DoorComponent;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;
import com.pacheco.game.util.ResourceUtil;

import java.io.File;
import java.io.IOException;

public class MapBuilder {

    private TileFactory tileFactory;
    private String mapsPath;
    private double tileW;
    private double tileH;

    public MapBuilder(double tileW, double tileH, String mapsPath) {
        tileFactory = new TileFactory(tileW, tileH);
        this.mapsPath = mapsPath;
        this.tileW = tileW;
        this.tileH = tileH;
    }

    public MapModel loadMapModel(EntityPool entityPool, String mapName) throws IOException {
        buildFromFile(entityPool, mapsPath + mapName + ".map");
        MapModel mapModel = loadMapModelFromFile(mapsPath + mapName + ".yaml");
        loadMapDoors(entityPool, mapModel);
        return mapModel;
    }

    private void loadMapDoors(EntityPool entityPool, MapModel mapModel) {
        for (DoorModel doorModel : mapModel.doors) {
            Entity door = new Entity();
            door.setComponent(DoorComponent.class, new DoorComponent(doorModel.box, doorModel));
            entityPool.addEntity(door);
        }
    }

    private MapModel loadMapModelFromFile(String mapModelPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        MapModel mapModel = mapper.readValue(new File(mapModelPath), MapModel.class);

        mapModel.playerInitialPos = new Vector2d(mapModel.playerInitialPos.x * tileW, mapModel.playerInitialPos.y * tileH);
        for (DoorModel door : mapModel.doors) {
            door.position = new Vector2d(door.position.x * tileW, door.position.y * tileH);
            door.playerPosition = new Vector2d(door.playerPosition.x * tileW, door.playerPosition.y * tileH);
            door.box = new Box(
                door.position.x - door.box.left * tileW,
                door.position.y - door.box.top * tileH,
                door.position.x + (door.box.right + 1) * tileW,
                door.position.y + (door.box.bottom + 1) * tileH
            );
        }
        return mapModel;
    }

    private void buildFromFile(EntityPool entityPool, String mapfile) {
        String mapString = ResourceUtil.getContentFromResource(mapfile);
        build(entityPool, mapString);
    }

    private void build(EntityPool entityPool, String mapString) {
        int column = 0;
        int line = 0;
        for (int i = 0; i < mapString.length(); i++) {
            char c = mapString.charAt(i);
            if (c == 'w') entityPool.addEntity(tileFactory.createSolid(SpritesHolder.WOOD_BLOCK, column, line));
            if (c == 'r') entityPool.addEntity(tileFactory.createSolid(SpritesHolder.ROCK_BLOCK, column, line));
            if (c == '.') entityPool.addEntity(tileFactory.createSurface(SpritesHolder.GRASS, column, line));
            if (c == 'm') entityPool.addEntity(tileFactory.createSurface(SpritesHolder.MUD, column, line));

            if (c == '\n') {
                line++;
                column = 0;
            } else {
                column++;
            }
        }
    }
}
