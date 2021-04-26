package com.davidalmarinho.java2dgame.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.game_objects.GameObject;

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

    /*public void draw(Graphics2D g2) {
        // Organising the rendering order of the gameObjects
        gameObjects.sort(GameObject.gameObjectSorter);
        for (GameObject gameObject : gameObjects) {
            // We will keep the real position of the gameObjects
            Vector2 realGameObjectPosition = gameObject.transform.position.copy();

            // GameObject with the camera rendering
            gameObject.transform.position = new Vector2(gameObject.transform.position.x - camera.position.x,
                    gameObject.transform.position.y - camera.position.y);
            gameObject.render(g2);

            // Replace the gameObject's position with its real position
            gameObject.transform.position = realGameObjectPosition;
        }
    }*/

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
            if (highestDepth < gameObject.depth) {
                highestDepth = gameObject.depth;
            }

            // Depth values found, so let's put game objects to the rendering array (buffer)
            if (depth == gameObject.depth) {
                buffer[index] = gameObject;
                index++;
            }

            // No more depth value have to be checked
            if (depth >= highestDepth) {
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

        batch.begin();
        for (GameObject gameObject : buffer) {
            gameObject.render(batch);
        }
        batch.end();
    }
}
