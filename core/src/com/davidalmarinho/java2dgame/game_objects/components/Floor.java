package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Floor extends Component {
    private final Sprite sprite;

    public Floor(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(SpriteBatch batch) {
        /*Sprite sprite = gameObject.getComponent(Sprite.class);
        if (sprite != null) {
            drawQuick(g2, sprite);
        } else {
            new ErrorFrame("Didn't find a wall sprite");
        }*/
        drawQuick(batch, sprite);
    }

    public Component copy() {
        return new Floor(this.sprite);
    }
}
