package com.davidalmarinho.data_structures;

import com.davidalmarinho.game_objects.components.Sprite;
import com.davidalmarinho.utils.WarningFrame;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    static Map<String, Sprite> spriteMap = new HashMap<>();

    public static boolean hasSprite(String pictureFile) {
        return spriteMap.containsKey(pictureFile);
    }

    public static Sprite getSprite(String pictureFile) {
        Sprite sprite = new Sprite(pictureFile);
        if (!hasSprite(pictureFile)) {
            // Add Sprite
            spriteMap.put(pictureFile, sprite);
        } else {
            File file = new File(pictureFile);
            String warningMsg = "Warning: File already loaded: '" + file.getAbsolutePath() + "'";
            new WarningFrame(warningMsg);
            System.out.println(warningMsg);
        }
        return sprite;
    }
}
