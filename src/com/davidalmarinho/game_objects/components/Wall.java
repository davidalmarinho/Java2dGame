package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.utils.ErrorFrame;

import java.awt.Graphics2D;

public class Wall extends Component {
    @Override
    public void render(Graphics2D g2) {
        Sprite sprite = gameObject.getComponent(Sprite.class);
        if (sprite != null) {
            drawQuick(g2, sprite);
        } else {
            new ErrorFrame("Didn't find a wall sprite");
        }
    }

    @Override
    public Component copy() {
        return new Wall();
    }
}
