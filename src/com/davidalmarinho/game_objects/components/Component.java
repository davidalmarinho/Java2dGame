package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;

import java.awt.Graphics2D;

public abstract class Component {
    public GameObject gameObject;

    public abstract void update(float dt);
    public abstract void render(Graphics2D g2);
}
