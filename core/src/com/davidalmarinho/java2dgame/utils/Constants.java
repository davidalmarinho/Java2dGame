package com.davidalmarinho.java2dgame.utils;

import com.davidalmarinho.java2dgame.data_structures.Transform;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.components.BoxBounds;
import com.davidalmarinho.java2dgame.game_objects.components.Floor;
import com.davidalmarinho.java2dgame.game_objects.components.Sprite;
import com.davidalmarinho.java2dgame.game_objects.components.Wall;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.scenes.LevelScene;

public class Constants {
    // Game/Screen
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int GAME_SCALE = 4;
    public static float WINDOW_SCALE_X = GAME_SCALE;
    public static float WINDOW_SCALE_Y = GAME_SCALE;

    // Levels
    public static final int TILE_SIZE = 16 * GAME_SCALE;
    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;

    // Gravity
    public static final float GRAVITY = 200;

    // LevelEditorScene Container
    public static final int X_CONTAINER = WINDOW_WIDTH / 2;
    public static final int Y_CONTAINER = WINDOW_HEIGHT / 2;
    public static final int BUTTON_WIDTH = 32;
    public static final int BUTTON_HEIGHT = 32;
    public static final int SPACE_BETWEEN_BUTTONS_HORIZONTAL = 16;
    public static final int SPACE_BETWEEN_BUTTONS_VERTICAL = 16;

    /**
     * To generate a Wall with all the configuration which it needs
     * @param position Wall's coordinates
     * @param sprite Wall's sprite
     * @return Will return a new Wall
     */
    public static GameObject createWall(Vector2 position, Sprite sprite) {
        GameObject gameObject = new GameObject("Wall", new Transform(position));
        gameObject.depth = 0;
        Wall wallComp = new Wall(sprite);
        gameObject.addComponent(wallComp);
        BoxBounds boxBounds = new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE);
        gameObject.addComponent(boxBounds);
        return gameObject;
    }

    public static GameObject createFloor(Vector2 position, Sprite sprite) {
        GameObject gameObject = new GameObject("Grass", new Transform(position));
        gameObject.depth = 0;
        Floor floorComp = new Floor(sprite);
        gameObject.addComponent(floorComp);
        return gameObject;
    }
}
