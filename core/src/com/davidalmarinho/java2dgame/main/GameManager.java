package com.davidalmarinho.java2dgame.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.scenes.LevelScene;
import com.davidalmarinho.java2dgame.scenes.Scene;
import com.davidalmarinho.java2dgame.scenes.Scenes;

public class GameManager extends Game {
    // Instance
    private static GameManager gameManager;
    // Components
    public Debugger debugger;
    // Scenes
    private Scene currentScene;
    private SpriteBatch batch;
    private boolean firstTime = true;
    private float time;

    // Using Singleton
    private GameManager() {
        debugger = Debugger.getInstance();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        changeScene(Scenes.LEVEL_SCENE);
    }

    @Override
    public void render() {
        this.update(Gdx.graphics.getDeltaTime());

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        // img.dispose();
    }

    public void changeScene(Scenes scene) {
        switch (scene) {
            case LEVEL_SCENE:
                currentScene = new LevelScene();
                currentScene.init();
                this.setScreen(currentScene);
                System.out.println("Inside Level Scene");
                break;
            case LEVEL_EDITOR_SCENE:
                /*currentScene = new LevelEditorScene();
                currentScene.init();*/
                System.out.println("Inside Level Editor Scene");
                break;
        }
    }

    public void update(float dt) {
        currentScene.update(dt);
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            debugger.debugging = !debugger.debugging;
        }
        debugger.update(dt);

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

    public SpriteBatch getBatch() {
        return batch;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
