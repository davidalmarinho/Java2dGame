package com.davidalmarinho.scenes;

import java.awt.*;

public abstract class Scene {
    public Scene() {
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void render(Graphics2D g2);
}
