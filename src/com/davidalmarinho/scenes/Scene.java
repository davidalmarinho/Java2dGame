package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.graphics.Renderer;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected List<GameObject> gameObjects;
    protected Renderer renderer;

    public Scene() {
        gameObjects = new ArrayList<>();
        renderer = new Renderer();
        init();
    }

    protected void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        renderer.submit(gameObject);
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void render(Graphics2D g2);
}
