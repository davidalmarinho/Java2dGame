package com.davidalmarinho.java2dgame.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.components.Floor;
import com.davidalmarinho.java2dgame.game_objects.components.Wall;
import com.davidalmarinho.java2dgame.main.GameManager;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    public List<GameObject> gameObjects;
    // private Camera camera;

    public Renderer() {
        gameObjects = new ArrayList<>();
        // this.camera = camera;
    }

    public void submit(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void clear() {
        gameObjects.clear();
    }

    public void draw(SpriteBatch batch) {
        if (batch == null) return;

        // Depth system
        /* The buffer (responsible to keep the game objects to be rendered) array must have the same
         * size of gameObjects List
         */
        GameObject[] buffer = new GameObject[gameObjects.size()];

        // Go into each GameObject and check its depth
        int depth = 0;
        int index = 0;  // To have control on the "memory"
        int highestDepth = Integer.MIN_VALUE;
        int control = 0;

        while (true) {
            GameObject gameObject = gameObjects.get(control).copy();

            // Keep highest depth
            for (GameObject go : gameObjects) {
                if (highestDepth < go.depth) {
                    highestDepth = go.depth;
                }
            }

            // Depth values found, so let's put game objects to the rendering array (buffer)
            if (depth == gameObject.depth) {
                buffer[index] = gameObject.copy();
                index++;
            }

            // No more depth value have to be checked
            if (depth > highestDepth) {
                break;
            }

            control++;

            /* If we checked all the Game Objects but have some depth values yet to check,
             * we will restart the loop
             */
            if (control >= gameObjects.size()) {
                control = 0;
                depth++;
            }
        }
        OrthographicCamera camera = GameManager.getInstance().getCurrentScene().getCamera();

        // Update camera's matrices before starting rendering process
        camera.update();

        // Tell how and where we will render the images on our screen
        batch.setProjectionMatrix(camera.combined);

        // Let's render!
        batch.begin();
        for (GameObject gameObject : buffer) {
            if (gameObject == null) {
                System.out.println("Error: Some game object cannot be rendered because " +
                        "are null in '" + getClass() + "':");
                for (int indexOfNull = 0; indexOfNull <= buffer.length; indexOfNull++) {
                    if (buffer[indexOfNull] == null) {
                        System.out.println("Null game object on "+ indexOfNull + " index");
                    }
                }
                System.exit(-1);
            }
            gameObject.render(batch);
        }
        batch.end();
    }
}
