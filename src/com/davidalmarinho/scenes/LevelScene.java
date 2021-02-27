package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.Player;
import com.davidalmarinho.utils.Transform;
import com.davidalmarinho.utils.Vector2;

import java.awt.*;

public class LevelScene extends Scene {
    @Override
    public void init() {
        GameObject player = new GameObject("Player", new Transform(new Vector2(60, 60)));
        Player playerComp = new Player();
        player.addComponent(playerComp);
        gameObjects.add(player);
    }

    @Override
    public void update(float dt) {
        for (GameObject g : gameObjects) {
            g.update(dt);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        for (GameObject g : gameObjects) {
            g.render(g2);
        }
    }
}
