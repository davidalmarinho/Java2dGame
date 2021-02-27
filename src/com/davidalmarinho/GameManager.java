package com.davidalmarinho;

import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.scenes.LevelEditorSCene;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.scenes.Scenes;
import com.davidalmarinho.utils.Constants;

import java.awt.*;

public class GameManager extends Engine {
    private Image background;
    private Graphics bufferGraphics;
    // Instance
    private static GameManager gameManager;
    // Components
    Window window;
    // Scenes
    Scene currentScene;

    public GameManager() {
        window = Window.get();
        changeScene(Scenes.LEVEL_SCENE);
    }

    public void changeScene(Scenes scene) {
        switch (scene) {
            case LEVEL_SCENE:
                currentScene = new LevelScene();
                System.out.println("Inside Level Scene");
                break;
            case LEVEL_EDITOR_SCENE:
                currentScene = new LevelEditorSCene();
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
            background = window.createImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            bufferGraphics = background.getGraphics();
        }

        renderOffScreen(g);

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

    /* Just get an instance of GameManager, because we just want a GameManager no 2 or 3,
     * and beacuse it's easier to have access to all stuff.
     */
    public static GameManager get() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }

        return gameManager;
    }
}
