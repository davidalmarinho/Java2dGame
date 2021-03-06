package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.graphics.Renderer;
import com.davidalmarinho.main.Camera;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    public List<GameObject> gameObjects;
    protected Renderer renderer;
    protected Camera camera;

    public Scene() {
        gameObjects = new ArrayList<>();
        camera = Camera.getInstance();
        renderer = new Renderer(camera);
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        renderer.submit(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
        renderer.remove(gameObject);
    }

    public void clearGameObjects() {
        gameObjects.clear();
        renderer.clear();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void render(Graphics2D g2);
}
