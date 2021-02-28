package com.davidalmarinho.graphics;

import com.davidalmarinho.game_objects.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    public List<GameObject> gameObjects;

    public Renderer() {
        gameObjects = new ArrayList<>();
    }

    public void submit(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void draw(Graphics2D g2) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(g2);
        }
    }
}
