package com.davidalmarinho.scenes;

import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.CameraControllers;
import com.davidalmarinho.game_objects.components.Grid;
import com.davidalmarinho.game_objects.components.SnapToGrid;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.ui.Container;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelEditorScene extends Scene {
    public Spritesheet editorSpritesheet;
    CameraControllers cameraControllers;
    public GameObject mouse;
    Grid grid;
    Container container;

    @Override
    public void init() {
        this.editorSpritesheet = new Spritesheet("assets/spritesheet.png", 4, 6, 16, 16);
        this.container = new Container(12, 4, 4);
        grid = new Grid();

        Constants.WORLD_WIDTH = 20 * Constants.TILE_SIZE;
        Constants.WORLD_HEIGHT = 20 * Constants.TILE_SIZE;

        mouse = new GameObject("CameraControllers", new Transform(new Vector2()));
        cameraControllers = new CameraControllers();
        mouse.addComponent(new SnapToGrid(Constants.TILE_SIZE, Constants.TILE_SIZE));
        mouse.addComponent(cameraControllers);
    }

    @Override
    public void update(float dt) {
        updateGameObjects(dt);

        grid.update(dt);
        if (!container.isSelectingItems()) {
            mouse.update(dt);
        }
        container.update(dt);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        renderer.draw(g2);
        if (!container.isSelectingItems()) {
            mouse.render(g2);
        }
        grid.render(g2);
        container.render(g2);
    }
}
