package com.davidalmarinho.scenes;

import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.CameraControllers;
import com.davidalmarinho.game_objects.components.Grid;
import com.davidalmarinho.game_objects.components.SnapToGrid;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.ui.ItemsTypes;
import com.davidalmarinho.ui.Container;
import com.davidalmarinho.ui.MainContainer;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelEditorScene extends Scene {
    public Spritesheet editorSpritesheet;
    CameraControllers cameraControllers;
    public GameObject mouse;
    Grid grid;
    MainContainer mainContainer = new MainContainer();

    @Override
    public void init() {
        this.editorSpritesheet = new Spritesheet("assets/spritesheet.png", 4, 6, 16, 16);
        mainContainer.append(new Container(editorSpritesheet, ItemsTypes.FLOOR, 12,
                new Vector2(Constants.X_CONTAINER, Constants.Y_CONTAINER), 4, 4));
        mainContainer.append(new Container(editorSpritesheet, ItemsTypes.WALL, 18,
                new Vector2(20, 20), 3, 6));

        grid = new Grid();

        Constants.WORLD_WIDTH = 20 * Constants.TILE_SIZE;
        Constants.WORLD_HEIGHT = 20 * Constants.TILE_SIZE;

        mouse = new GameObject("CameraControllers", new Transform(new Vector2()));
        cameraControllers = new CameraControllers();
        mouse.addComponent(new SnapToGrid(Constants.TILE_SIZE, Constants.TILE_SIZE));
        mouse.addComponent(cameraControllers);
        //mouse.addComponent(mainContainer);
    }

    @Override
    public void update(float dt) {
        updateGameObjects(dt);
        grid.update(dt);
        mouse.update(dt);
        mainContainer.update(dt);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        renderer.draw(g2);
        grid.render(g2);
        mouse.render(g2);
        mainContainer.render(g2);
    }
}
