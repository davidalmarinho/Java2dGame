package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.Player;
import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelScene extends Scene {
   Spritesheet spritesheet;

    @Override
    public void init() {
        GameObject player = new GameObject("Player", new Transform(new Vector2(60, 60)));
        spritesheet = new Spritesheet("assets/spritesheet.png", 4, 6,
                16, 16);
        new Spritesheet("assets/spritesheet.png", 4, 6, 16, 16);
        Player playerComp = new Player(spritesheet.sprites.get(14));
        player.transform.scale.x = 4;
        player.transform.scale.y = 4;
        player.addComponent(playerComp);
        addGameObject(player);
    }

    @Override
    public void update(float dt) {
        for (GameObject g : gameObjects) {
            g.update(dt);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        renderer.draw(g2);
    }
}
