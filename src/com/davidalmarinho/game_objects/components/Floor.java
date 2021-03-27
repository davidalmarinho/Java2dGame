package com.davidalmarinho.game_objects.components;

import java.awt.Graphics2D;

public class Floor extends Component {
    private final Sprite sprite;

    public Floor(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(Graphics2D g2) {
        drawQuick(g2, sprite);
    }

    public Component copy() {
        return new Floor(this.sprite);
    }
}
