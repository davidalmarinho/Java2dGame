package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Component {
    public GameObject gameObject;

    public void init() {

    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {

    }

    public abstract Component copy();

    protected void drawQuick(SpriteBatch batch, Sprite sprite) {
        /*Vector2 position = gameObject.transform.position.copy();
        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(position.x, position.y);
        transform.scale(gameObject.transform.scale.x, gameObject.transform.scale.y);*/
        //g2.drawImage(sprite.image, transform, null);
        batch.draw(sprite.image,
                gameObject.transform.position.x,
                gameObject.transform.position.y,
                gameObject.transform.scale.x * 16,
                gameObject.transform.scale.y * 16);
    }
}
