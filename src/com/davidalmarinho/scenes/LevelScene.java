package com.davidalmarinho.scenes;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.world.Levels;
import com.davidalmarinho.game_objects.components.Player;
import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelScene extends Scene {
   Spritesheet spritesheet;
   Levels levels;
   int level = 0;
   public GameObject player;
   private float parseLevelFpsTest;
   private boolean firstTimeParseLevelTest = true;

    @Override
    public void init() {
        this.player = new GameObject("Player", new Transform(new Vector2(60, 60)));
        spritesheet = new Spritesheet("assets/spritesheet.png", 4, 6,
                16, 16);
        levels = Levels.getInstance("assets/levels");

        // Creating a spritesheet with the same location of the spritesheet above to check if AssetPool is working fine
        new Spritesheet("assets/spritesheet.png", 4, 6, 16, 16);

        Player playerComp = new Player(spritesheet.sprites.get(14));
        player.transform.scale.x = 4;
        player.transform.scale.y = 4;
        this.player.addComponent(playerComp);
        addGameObject(player);

        // new Levels("assets/levels"); <- Was a test to check the AssetPool Structure (to test again we need to set Levels' constructor as public

        Levels.getInstance("assets/levels").levels.get(level).init();
    }

    @Override
    public void update(float dt) {
        for (GameObject g : gameObjects) {
            g.update(dt);
        }

        // Just to check if the levels management is going well (3 seconds and it switches to the 2nd level
        parseLevelFpsTest += dt;
        if (parseLevelFpsTest >= 3.0 && firstTimeParseLevelTest) {
            level++;
            Levels.getInstance("assets/levels").levels.get(level).init();
            parseLevelFpsTest = 0;
            firstTimeParseLevelTest = false;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        renderer.draw(g2);
    }
}
