package com.davidalmarinho.levels;

import com.davidalmarinho.game_objects.MapColors;
import com.davidalmarinho.game_objects.components.*;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.ErrorFrame;
import com.davidalmarinho.utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private BufferedImage map;
    private int width, height;

    public Level(String levelFile) {
        // Load files
        try {
            File file = new File(levelFile);
            this.map = ImageIO.read(file);

        } catch (IOException e) {
            String error = "Couldn't load '" + new File(levelFile).getAbsolutePath() + "'.";
            System.out.println(error);
            new ErrorFrame(error);
            e.printStackTrace();
        }
    }

    public void init() {
        this.width = map.getWidth();
        this.height = map.getHeight();

        // World's real size
        Constants.WORLD_WIDTH = width * Constants.TILE_SIZE;
        Constants.WORLD_HEIGHT = height * Constants.TILE_SIZE;

        int[] pixels = map.getRGB(0, 0, width, height, null, 0, width);

        // Temporary list of game objects that will be parte to a Scene
        List<GameObject> gameObjects = new ArrayList<>();

        // Clear the gameObjects' list
        GameManager.getInstance().getCurrentScene().clearGameObjects();

        // To don't have always write looooooong codes
        LevelScene levelScene = (LevelScene) GameManager.getInstance().getCurrentScene();

        // Go throw each pixels and check its color. Depending of its color, we will place a new GameObject
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GameObject gameObject = null;
                Vector2 currentWorldCoordinates = new Vector2(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);

                int currentPixel = pixels[x + y * width];

                // Replace Player
                if (currentPixel == MapColors.PLAYER_COLOR.getColor()) {
                    gameObject = new GameObject("Player",
                            new Transform(new Vector2(currentWorldCoordinates)));

                    gameObject.depth = 1;

                    Player playerComp = new Player(levelScene.getSpritesheet().sprites.get(14));
                    gameObject.addComponent(playerComp);

                    RigidBody rigidBody = new RigidBody(new Vector2(160, Constants.GRAVITY));
                    gameObject.addComponent(rigidBody);

                    BoxBounds boxBounds = new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE);
                    gameObject.addComponent(boxBounds);

                    levelScene.player = gameObject;
                    gameObjects.add(gameObject);

                    // Place buildings, ground...
                } else if (currentPixel == MapColors.WALL_COLOR.getColor()) {
                    gameObject = new GameObject("Wall", new Transform(new Vector2(currentWorldCoordinates)));
                    Wall wallComp = new Wall(levelScene.getSpritesheet().sprites.get(3));
                    gameObject.addComponent(wallComp);
                    BoxBounds boxBounds = new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE);
                    gameObject.addComponent(boxBounds);
                    gameObjects.add(gameObject);
                } else if (currentPixel == MapColors.FLOOR_COLOR.getColor()) {
                    gameObject = new GameObject("Grass", new Transform(new Vector2(currentWorldCoordinates)));
                    Floor floorComp = new Floor(levelScene.getSpritesheet().sprites.get(2));
                    gameObject.addComponent(floorComp);
                    gameObjects.add(gameObject);
                }

                // TODO: Need a more intelligent algorithm, still thinking on it
                /* Bug fix: Create a floor and place it, to after, when we place gameObjects,
                 * don't let just a black square. If you stayed confused, just comment this part of code,
                 * run it and move the player :)
                 */
                if (gameObject != null && gameObject.getComponent(Floor.class) == null
                        && gameObject.getComponent(Wall.class) == null) {
                    placeFloorInEmptyPlaces(gameObjects, new Vector2(currentWorldCoordinates));
                }
            }
        }

        // Parse our game objects list to a Scene
        for (GameObject gameObject : gameObjects) {
            GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
        }

        System.out.println("GameObjects size: " + gameObjects.size());
    }

    private void placeFloorInEmptyPlaces(List<GameObject> gameObjects, Vector2 worldCoordinates) {
        GameObject grass = new GameObject("Grass", new Transform(worldCoordinates));
        Floor floorComp = new Floor(((LevelScene) GameManager.getInstance().getCurrentScene()).
                getSpritesheet().sprites.get(2));
        grass.addComponent(floorComp);
        gameObjects.add(grass);
    }

    /*@Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillRect(0, 0, width * 64, height * 64);
    }*/
}
