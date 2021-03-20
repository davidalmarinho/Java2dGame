package com.davidalmarinho.scenes;

import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.CameraControllers;
import com.davidalmarinho.game_objects.components.Grid;
import com.davidalmarinho.game_objects.components.SnapToGrid;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelEditorScene extends Scene {
    CameraControllers cameraControllers;
    GameObject mouse;
    Grid grid;

    @Override
    public void init() {
        grid = new Grid();

        Constants.WORLD_WIDTH = 20 * 64;
        Constants.WORLD_HEIGHT = 20 * 64;

        mouse = new GameObject("CameraControllers", new Transform(new Vector2()));
        cameraControllers = new CameraControllers();
        mouse.addComponent(cameraControllers);
        mouse.addComponent(new SnapToGrid(Constants.TILE_SIZE, Constants.TILE_SIZE));
    }

    @Override
    public void update(float dt) {
        updateGameObjects(dt);

        grid.update(dt);
        mouse.update(dt);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        mouse.render(g2);
        grid.render(g2);
        renderer.draw(g2);
    }
}
