package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected List<GameObject> gameObjects;

    public Scene() {
        gameObjects = new ArrayList<>();
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void render(Graphics2D g2);
}
