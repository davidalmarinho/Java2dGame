package com.davidalmarinho.graphics;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    public List<GameObject> gameObjects;
    private Camera camera;

    public Renderer(Camera camera) {
        gameObjects = new ArrayList<>();
        this.camera = camera;
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

    public void draw(Graphics2D g2) {
        // Organising the rendering order of the gameObjects
        gameObjects.sort(GameObject.gameObjectSorter);
        for (GameObject gameObject : gameObjects) {
            // We will keep the real position of the gameObjects
            Vector2 realGameObjectPosition = gameObject.transform.position;

            // GameObject with the camera rendering
            gameObject.transform.position = new Vector2(gameObject.transform.position.x - camera.position.x,
                    gameObject.transform.position.y - camera.position.y);
            gameObject.render(g2);

            // Replace the gameObject's position with its real position
            gameObject.transform.position = realGameObjectPosition;
        }
    }
}
