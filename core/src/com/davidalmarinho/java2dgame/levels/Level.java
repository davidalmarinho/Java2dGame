package com.davidalmarinho.java2dgame.levels;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.davidalmarinho.java2dgame.data_structures.Transform;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.MapColors;
import com.davidalmarinho.java2dgame.game_objects.components.BoxBounds;
import com.davidalmarinho.java2dgame.game_objects.components.Floor;
import com.davidalmarinho.java2dgame.game_objects.components.Player;
import com.davidalmarinho.java2dgame.game_objects.components.RigidBody;
import com.davidalmarinho.java2dgame.game_objects.components.Wall;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.scenes.LevelScene;
import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.ErrorFrame;
import com.davidalmarinho.java2dgame.utils.Vector2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Level {
    private final Pixmap map;
    private int width, height;

    public Level(FileHandle levelFile) {
        // Load files
        this.map = new Pixmap(levelFile);
        /*String error = "Couldn't load '" + file.getAbsolutePath() + "'.";
        System.out.println(error);
        new ErrorFrame(error);*/
    }

    public void init() {
        this.width = map.getWidth();
        this.height = map.getHeight();

        // World's real size
        Constants.WORLD_WIDTH = width * Constants.TILE_SIZE;
        Constants.WORLD_HEIGHT = height * Constants.TILE_SIZE;

        // Temporary list of game objects that will be parte to a Scene
        List<GameObject> gameObjects = new ArrayList<>();

        // Clear the gameObjects' list
        // GameManager.getInstance().getCurrentScene().clearGameObjects();

        // To don't have always write looooooong codes
        LevelScene levelScene = (LevelScene) GameManager.getInstance().getCurrentScene();


        // Go throw each pixels and check its color. Depending of its color, we will place a new GameObject
        for (int x = 0; x < width; x++) {
            for (int y = height; y > 0; y--) {
                GameObject gameObject = null;
                
                Vector2 currentWorldCoordinates = new Vector2(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
                int currentPixel = map.getPixel(x, y);

                // Replace Player
                if (currentPixel == MapColors.PLAYER_COLOR.getColor()) {
                    gameObject = new GameObject("Player",
                            new Transform(currentWorldCoordinates.copy()));
                    gameObject.depth = 1;

                    Player playerComp = new Player(levelScene.spritesheet.sprites.get(14));
                    gameObject.addComponent(playerComp);

                    RigidBody rigidBody = new RigidBody(new Vector2(160, 160));
                    gameObject.addComponent(rigidBody);

                    BoxBounds boxBounds = new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE);
                    gameObject.addComponent(boxBounds);

                    // levelScene.player = gameObject;
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
                /*if (gameObject != null && gameObject.getComponent(Floor.class) == null
                        && gameObject.getComponent(Wall.class) == null) {
                    GameObject gameObjectToBlankSpaces = Constants.createFloor(currentWorldCoordinates.copy(),
                            levelScene.spritesheet.sprites.get(2));
                    gameObjects.add(gameObjectToBlankSpaces);
                }*/
            }
        }

        // Parse our game objects list to a Scene
        for (GameObject gameObject : gameObjects) {
            GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
        }
    }

    /*@Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillRect(0, 0, width * 64, height * 64);
    }*/
}
