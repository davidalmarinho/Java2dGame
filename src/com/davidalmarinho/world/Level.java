package com.davidalmarinho.world;

import com.davidalmarinho.GameManager;
import com.davidalmarinho.data_structures.AssetPool;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.scenes.LevelScene;
import com.davidalmarinho.utils.WarningFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level {
    private BufferedImage map;
    private int width, height;

    public Level(String levelFile) {
        // Load files
        try {
            File file = new File(levelFile);
            this.map = ImageIO.read(file);

        } catch (IOException e) {
            String error = "Error: Couldn't load '" + new File(levelFile).getAbsolutePath() + "'.";
            System.out.println(error);
            new WarningFrame(error);
            e.printStackTrace();
        }
    }

    public void init() {
        this.width = map.getWidth();
        this.height = map.getHeight();
        int[] pixels = map.getRGB(0, 0, width, height, null, 0, width);

        // Go throw each pixels and check its color. Depending of its color, we will place a new GameObject
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = pixels[x + y * width];

                if (currentPixel == 0xFFff0000) {
                    GameObject player = ((LevelScene) GameManager.getInstance().getCurrentScene()).player;
                    player.transform.position.x = x;
                    player.transform.position.y = y;
                }
            }
        }
    }

    /*@Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillRect(0, 0, width * 64, height * 64);
    }*/
}
