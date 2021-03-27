package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Component {
    public GameObject gameObject;

    public void init() {

    }

    public void update(float dt) {

    }

    public void render(Graphics2D g2) {

    }

    public abstract Component copy();

    protected void drawQuick(Graphics2D g2, Sprite sprite) {
        Vector2 position = gameObject.transform.position.copy();
        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(position.x, position.y);
        transform.scale(gameObject.transform.scale.x, gameObject.transform.scale.y);
        g2.drawImage(sprite.image, transform, null);
    }
}
