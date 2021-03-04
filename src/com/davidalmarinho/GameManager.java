package com.davidalmarinho;

import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.scenes.LevelEditorScene;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.scenes.Scenes;
import com.davidalmarinho.utils.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameManager extends Engine {
    // Graphics
    private BufferedImage background = null;
    private Graphics bufferGraphics = null;
    // Instance
    private static GameManager gameManager;
    // Components
    Window window;
    // Scenes
    private Scene currentScene;

    // Using Singleton
    private GameManager() {
        window = Window.getInstance();
    }

    /* We need here an init method, because sometimes, we want to access to the instance of gameManager if init()
     * method in the Scene's classes. So we only initialize the scenes after initializing GameManager
     */
    @Override
    public void init() {
        changeScene(Scenes.LEVEL_SCENE);
    }

    public void changeScene(Scenes scene) {
        switch (scene) {
            case LEVEL_SCENE:
                currentScene = new LevelScene();
                currentScene.init();
                System.out.println("Inside Level Scene");
                break;
            case LEVEL_EDITOR_SCENE:
                currentScene = new LevelEditorScene();
                currentScene.init();
                System.out.println("Inside Level Editor Scene");
                break;
        }
    }

    @Override
    public void update(float dt) {
        currentScene.update(dt);
        // System.out.println("FPS: " + 1.0 / dt);
    }

    public void draw(Graphics g) {
        if (background == null) {
            background = (BufferedImage) window.createImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            bufferGraphics = background.getGraphics();
        }

        renderOffScreen(bufferGraphics);

        g.drawImage(background, 0, 0, window.getWidth(), window.getHeight(), null);
    }

    public void renderOffScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.render(g2);
    }

    @Override
    public void render() {
        draw(window.getGraphics());
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    /* Just get an instance of GameManager, because we just want a GameManager not 2 or 3,
     * and because it's easier to have access to all stuff.
     */
    public static GameManager getInstance() {
        if (GameManager.gameManager == null) {
            GameManager.gameManager = new GameManager();
        }

        return GameManager.gameManager;
    }
}
