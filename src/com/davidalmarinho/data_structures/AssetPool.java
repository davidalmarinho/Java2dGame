package com.davidalmarinho.data_structures;

import com.davidalmarinho.game_objects.components.Sprite;
import com.davidalmarinho.utils.WarningFrame;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    /* HashMap contains a key and an other variable (key, variable)
     * Ex.: Map <String, String> teams = new HashMap<>();
     *      Our hash map values are ("", "")
     *      teams.put("Portugal", "Porto");
     *      Our hash map values are ("Portugal", "Porto")
     *
     *      To catch the country's team:
     *      teams.containsKey("Portugal");
     *      To catch the team:
     *      teams.get("Porto");
     *
     * So, with HashMap we can get already existing values.
     * This will turn our code more safe :D
     */
    static Map<String, Sprite> spriteMap = new HashMap<>();

    // Just checking if we already have a Sprite with the same location
    public static boolean hasSprite(String pictureFile) {
        return spriteMap.containsKey(pictureFile);
    }

    public static Sprite getSprite(String pictureFile) {
        Sprite sprite = new Sprite(pictureFile);
        if (!hasSprite(pictureFile)) {
            // Add Sprite
            spriteMap.put(pictureFile, sprite);
        } else {
            // Throw a warning
            File file = new File(pictureFile);
            String warningMsg = "Warning: File already loaded: '" + file.getAbsolutePath() + "'";
            new WarningFrame(warningMsg);
            System.out.println(warningMsg);
        }
        return sprite;
    }
}
