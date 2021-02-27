package com.davidalmarinho.game_objects.components;

import java.awt.*;

public class Player extends Component {
    @Override
    public void update(float dt) {
        // System.out.println("Player ticked!");
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255, 100, 100));
        g2.fillRect(gameObject.transform.position.x, gameObject.transform.position.y, 64, 64);
    }
}
