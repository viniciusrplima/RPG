package com.pacheco.game.system;

import com.pacheco.game.Constants;
import com.pacheco.game.component.BoundingBoxComponent;
import com.pacheco.game.component.DoorComponent;
import com.pacheco.game.component.PositionComponent;
import com.pacheco.game.core.Box;
import com.pacheco.game.entity.Entity;
import com.pacheco.game.entity.EntityPool;
import com.pacheco.game.map.DoorModel;
import com.pacheco.game.map.MapBuilder;
import com.pacheco.game.map.MapModel;

import java.io.IOException;

public class MapSystem {

    private MapBuilder mapBuilder;
    private EntityPool entityPool;
    private MapModel mapModel;
    private Entity player;

    public MapSystem(EntityPool entityPool, Entity player) {
        this.entityPool = entityPool;
        this.mapBuilder = new MapBuilder(Constants.TILE_W, Constants.TILE_H, Constants.MAPS_PATH);
        this.player = player;
        try {
            this.mapModel = mapBuilder.loadMapModel(entityPool, Constants.INITIAL_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.getComponent(PositionComponent.class).position = mapModel.playerInitialPos;
    }

    public void changeMap(DoorModel doorModel) {
        entityPool.clear();
        try {
            mapModel = mapBuilder.loadMapModel(entityPool, doorModel.destinationMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (DoorModel destDoor : mapModel.doors) {
            if (destDoor.name.equals(doorModel.destinationDoor)) {
                player.getComponent(PositionComponent.class).position = destDoor.playerPosition.clone();
                break;
            }
        }
        entityPool.addEntity(player);
    }

    public void update() {
        for (Entity entity : entityPool.getEntitiesByComponent(DoorComponent.class)) {
            DoorComponent doorComponent = entity.getComponent(DoorComponent.class);
            Box boundingBox = player.getComponent(BoundingBoxComponent.class)
                    .box.add(player.getComponent(PositionComponent.class).position);
            if (boundingBox.intersects(doorComponent.boundingBox)) {
                System.out.println("done");
                changeMap(doorComponent.doorModel);
            }
        }
    }
}
