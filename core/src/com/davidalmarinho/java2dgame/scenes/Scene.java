package com.davidalmarinho.java2dgame.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.graphics.Renderer;
import com.davidalmarinho.java2dgame.main.Camera;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements Screen {
    //protected final GameManager gameManager;
    public List<GameObject> gameObjects;
    protected Renderer renderer;
    protected Camera camera;

    public Scene() {
        //this.gameManager = gameManager;
        renderer = new Renderer();
        gameObjects = new ArrayList<>();
        camera = Camera.getInstance();
        // camera.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    public abstract void init();

    public void updateGameObjects(float dt) {
        // camera.update();
        for (GameObject g : gameObjects) {
            g.update(dt);
        }
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        renderer.submit(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
        // renderer.remove(gameObject);
    }

    public void clearGameObjects() {
        gameObjects.clear();
        // renderer.clear();
    }

    public abstract void update(float dt);
    // public abstract void render(SpriteBatch batch);


    public Camera getCamera() {
        return camera;
    }
}
