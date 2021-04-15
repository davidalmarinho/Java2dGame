package com.davidalmarinho.main;

import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.input.KeyboardInput;
import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.scenes.LevelEditorScene;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.scenes.Scenes;
import com.davidalmarinho.utils.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameManager extends Engine {
    // Graphics
    private final BufferedImage background;
    // Instance
    private static GameManager gameManager;
    // Components
    Window window;
    KeyboardInput keyboardInput;
    public MouseInput mouseInput;
    public Debugger debugger;
    // Scenes
    private Scene currentScene;

    // Using Singleton
    private GameManager() {
        background = new BufferedImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        window = Window.getInstance(mouseInput);
        mouseInput = MouseInput.getInstance();
        keyboardInput = KeyboardInput.getInstance();
        addListeners(window);
        debugger = Debugger.getInstance();
    }

    private void addListeners(Window window) {
        window.addKeyListener(keyboardInput);
        window.addMouseListener(mouseInput);
        window.addMouseMotionListener(mouseInput);
    }

    /* We need here an init method, because sometimes, we want to access to the instance of gameManager if init()
     * method in the Scene's classes. So we only initialize the scenes after initializing GameManager
     */
    @Override
    public void init() {
        changeScene(Scenes.LEVEL_SCENE);
        window.requestFocus();
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
        // Update SCALES to, when we resize the window we need the exact position of the mouse in the game background
        Constants.WINDOW_SCALE_X = (float) (window.getWidth() / (double) background.getWidth());
        Constants.WINDOW_SCALE_Y = (float) (window.getHeight() / (double) background.getHeight());

        currentScene.update(dt);
        if (keyboardInput.isKeyDown(KeyEvent.VK_F3)) {
            debugger.debugging = !debugger.debugging;
        }
        debugger.update(dt);
        keyboardInput.update();
        mouseInput.update();
    }

    public void draw(Graphics g) {
        renderOffScreen(g);
        debugger.draw(g);
    }

    public void renderOffScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.render(g2);
    }

    @Override
    public void render() {
        /* BufferStrategies are a specie of screen workers.
         * What they do so?
         * Very simple, depending in how many BufferStrategies we create, they will create surrogate's images
         * that will be waiting to be rendered.
         * For example, we have created 3 buffer strategies.
         *
         *               Queue images' list                Image that is been rendering
         *
         *   ________________          ________________         ________________
         *  |                |        |                |       |                |
         *  |   IMAGE 3      |        |   IMAGE 2      |       |   IMAGE 1      |
         *  | waiting to be  | ---->  | waiting to be  | ----> | image that is  |
         *  | rendered       |        | rendered       |       | been rendering |
         *  |________________|        |________________|       |________________|
         *
         *  So, we need at least 1 BufferStrategy to render our game.
         */
        BufferStrategy bs = window.getBufferStrategy();
        // Check if we have BufferStrategies and only keep moving when we have created them
        if (bs == null) {
            window.createBufferStrategy(2);
            return;
        }

        Graphics g = background.getGraphics();

        draw(g);

        g = bs.getDrawGraphics();
        g.drawImage(background, 0, 0, window.getWidth(), window.getHeight(), null);
        bs.show();
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
