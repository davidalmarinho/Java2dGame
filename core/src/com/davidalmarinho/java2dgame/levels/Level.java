package com.davidalmarinho.java2dgame.levels;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.davidalmarinho.java2dgame.data_structures.Transform;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.MapColors;
import com.davidalmarinho.java2dgame.game_objects.components.BoxBounds;
import com.davidalmarinho.java2dgame.game_objects.components.Floor;
import com.davidalmarinho.java2dgame.game_objects.components.Player;
import com.davidalmarinho.java2dgame.game_objects.components.RigidBody;
import com.davidalmarinho.java2dgame.game_objects.components.Sprite;
import com.davidalmarinho.java2dgame.game_objects.components.Wall;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.scenes.LevelScene;
import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.Vector2;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final Pixmap map;

    public Level(FileHandle fileHandle) {
        // Create level
        map = new Pixmap(fileHandle); // Kept in BGRA (Blue, Green, Red, Alpha) format, not ARGB
    }

    public void init() {
        int width = map.getWidth();
        int height = map.getHeight();

        // World's real size
        Constants.WORLD_WIDTH = width * Constants.TILE_SIZE;
        Constants.WORLD_HEIGHT = height * Constants.TILE_SIZE;

        // Temporary list of game objects that will belong to the Scene
        List<GameObject> gameObjects = new ArrayList<>();

        // Clear the gameObjects' list
        GameManager.getInstance().getCurrentScene().clearGameObjects();

        // To don't have always write looooooong codes
        LevelScene levelScene = (LevelScene) GameManager.getInstance().getCurrentScene();

        // Save the map in an IntBuffer
        IntBuffer intBuffer = map.getPixels().asIntBuffer();

        // Go throw each pixels and check its color. Depending of its color, we will place a new GameObject
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GameObject gameObject = null;

                // The pixels' map are upside down, so we have to do a trick in the 2nd parameter
                Vector2 currentWorldCoordinates = new Vector2(x * Constants.TILE_SIZE,
                        (height - y - 1) * Constants.TILE_SIZE);

                // Get the current pixel of the .png file
                int currentPixel = intBuffer.get(x + y * width);

                // Create Player
                if (currentPixel == MapColors.PLAYER_COLOR.getColor()) {
                    gameObject = new GameObject("Player",
                            new Transform(currentWorldCoordinates.copy()));
                    gameObject.depth = 1;

                    Player playerComp = new Player(levelScene.spritesheet.getSprites().get(8));
                    gameObject.addComponent(playerComp);

                    gameObject.addComponent(new RigidBody(new Vector2(100.0f, 100.0f)));
                    gameObject.addComponent(new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE));

                    gameObjects.add(gameObject);

                    // Place buildings, ground...
                } else if (currentPixel == MapColors.WALL_COLOR.getColor()) {
                    gameObject = Constants.createWall(currentWorldCoordinates.copy(),
                            levelScene.spritesheet.sprites.get(3));
                    gameObjects.add(gameObject);

                } else if (currentPixel == MapColors.FLOOR_COLOR.getColor()) {
                    gameObject = Constants.createFloor(currentWorldCoordinates.copy(),
                            levelScene.spritesheet.sprites.get(2));
                    gameObjects.add(gameObject);
                }

                // TODO: Need a more intelligent algorithm, still thinking on it
                /* Bug fix: Create a floor and place it, to after, when we place gameObjects,
                 * don't let just a black square. If you stayed confused, just comment this part of code,
                 * run it and move the player :)
                 */
                if (gameObject != null && gameObject.getComponent(Floor.class) == null
                        && gameObject.getComponent(Wall.class) == null) {

                    GameObject gameObjectToBlankSpaces = Constants.createFloor(currentWorldCoordinates.copy(),
                            levelScene.spritesheet.sprites.get(2));
                    gameObjects.add(gameObjectToBlankSpaces);
                }
            }
        }

        // Parse our game objects list to a Scene
        for (GameObject gameObject : gameObjects) {
            if (gameObject == null) {
                System.out.println("Error: Found a null game object in '" + getClass() + "'");
            }
            GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
        }
    }

    /*@Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillRect(0, 0, width * 64, height * 64);
    }*/
}
