package com.davidalmarinho.game_objects.components;

import java.awt.Graphics2D;

public class Wall extends Component {
    private final Sprite sprite;

    public Wall(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(Graphics2D g2) {
        drawQuick(g2, sprite);
    }
}
