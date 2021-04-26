package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Graphics2D;

public class Wall extends Component {
    private final Sprite sprite;

    public Wall(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch batch) {
        drawQuick(batch, sprite);
    }

    @Override
    public Component copy() {
        return new Wall(this.sprite);
    }
}
