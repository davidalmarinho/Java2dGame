package com.davidalmarinho.java2dgame.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.components.BoxBounds;
import com.davidalmarinho.java2dgame.graphics.Renderer;
import com.davidalmarinho.java2dgame.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements Screen {
    //protected final GameManager gameManager;
    public List<GameObject> gameObjects;
    protected Renderer renderer;
    protected OrthographicCamera camera;
    protected StretchViewport viewport;

    public Scene() {
        //this.gameManager = gameManager;
        renderer = new Renderer();
        gameObjects = new ArrayList<>();
        // camera = Camera.getInstance();
        camera = new OrthographicCamera(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        camera.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        viewport = new StretchViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    public abstract void init();

    protected void lockCamera(GameObject gameObject) {
        // Check if we have a BoxBounds component
        BoxBounds boxBounds = gameObject.getComponent(BoxBounds.class);
        if (boxBounds == null) {
            System.out.println("Impossible to lock '" + gameObject.name + "' because it doesn't " +
                    "have a BoxBounds component.");
            System.exit(-1);
            return;
        }

        // Pick the screen's lengths
        float middleXScreen = (float) (Constants.WINDOW_WIDTH / 2);
        float middleYScreen = (float) (Constants.WINDOW_HEIGHT / 2);

        // Pick world's lengths
        float worldWidth = Constants.WORLD_WIDTH;
        float worldHeight = Constants.WORLD_HEIGHT;

        // Pick gameObject's lengths
        float gameObjectHalfWidth = (float) (boxBounds.width / 2);
        float gameObjectHalfHeight = (float) (boxBounds.height / 2);

        if (gameObject.transform.position.x < middleXScreen - gameObjectHalfWidth) {
            // Fix rendering of left's world
            camera.position.x = (int) middleXScreen;
        } else if (gameObject.transform.position.x > worldWidth - middleXScreen - gameObjectHalfWidth) {
            // Fix rendering of right's world
            camera.position.x = (int) worldWidth - middleXScreen;
        } else {
            camera.position.x = (int) gameObject.transform.position.x + gameObjectHalfWidth;
        }

        if (gameObject.transform.position.y < middleYScreen - gameObjectHalfHeight) {
            // Fix rendering of bot's world
            camera.position.y = (int) middleYScreen;
        } else if (gameObject.transform.position.y > worldHeight - middleYScreen - gameObjectHalfHeight) {
            // Fix rendering of top's world
            camera.position.y = (int) worldHeight - middleYScreen;
        } else {
            camera.position.y = (int) gameObject.transform.position.y + gameObjectHalfHeight;
        }
    }

    public void updateGameObjects(float dt) {
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
        renderer.remove(gameObject);
    }

    public void clearGameObjects() {
        gameObjects.clear();
        renderer.clear();
    }

    public abstract void update(float dt);

    public OrthographicCamera getCamera() {
        return camera;
    }
}
