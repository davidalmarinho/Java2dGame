package com.davidalmarinho.main;

import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.input.KeyboardInput;
import com.davidalmarinho.scenes.LevelEditorScene;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.scenes.Scenes;
import com.davidalmarinho.utils.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameManager extends Engine {
    // Graphics
    private BufferedImage background = null;
    private Graphics bufferGraphics = null;
    // Instance
    private static GameManager gameManager;
    // Components
    Window window;
    KeyboardInput keyboardInput;
    Debugger debugger;
    // Scenes
    private Scene currentScene;

    // Using Singleton
    private GameManager() {
        window = Window.getInstance();
        keyboardInput = KeyboardInput.getInstance(window);
        debugger = Debugger.getInstance();
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
        if (keyboardInput.isKeyDown(KeyEvent.VK_F3)) {
            debugger.debugging = !debugger.debugging;
        }
        debugger.update(dt);
        keyboardInput.update();
    }

    public void draw(Graphics g) {
        if (background == null) {
            background = (BufferedImage) window.createImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            bufferGraphics = background.getGraphics();
        }

        renderOffScreen(bufferGraphics);
        debugger.draw(bufferGraphics);

        /* window.getComponent(0).getY() or window.getRootPane().getY()
         * is the first value under of the top bar (the bar where you can minimize and maximise the window)
         *  in the window.
         * We will use it, because, by some way, that bar steals us some rendering space.
         * So we have to start render at the first pixel under of the bar,
         * so in the y's coordinates of the JRootPane.
         */
        g.drawImage(background, 0, window.getRootPane().getY(), window.getWidth(),
                window.getRootPane().getHeight(), null);
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

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
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
