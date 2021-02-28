package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.Player;
import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.utils.Vector2;

import java.awt.*;

public class LevelScene extends Scene {
    @Override
    public void init() {
        GameObject player = new GameObject("Player", new Transform(new Vector2(60, 60)));
        Spritesheet spritesheet = new Spritesheet("assets/spritesheet.png", 4, 6,
                16, 16);
        new Spritesheet("assets/spritesheet.png", 4, 6,
                16, 16);
        player.addComponent(spritesheet);
        Player playerComp = new Player(spritesheet.sprites.get(14));
        player.addComponent(playerComp);
        player.transform.scale.x = 40;
        player.transform.scale.y = 40;
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
