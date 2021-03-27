package com.davidalmarinho.game_objects.components;

import java.awt.Graphics2D;

public class Player extends Component {
    private final Sprite sprite;

    public Player(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Graphics2D g2) {
        drawQuick(g2, sprite);
    }

    @Override
    public Component copy() {
        return new Player(this.sprite);
    }
}
