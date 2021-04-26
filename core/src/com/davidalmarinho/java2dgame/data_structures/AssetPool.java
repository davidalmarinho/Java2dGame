package com.davidalmarinho.java2dgame.data_structures;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.davidalmarinho.java2dgame.game_objects.components.Sprite;
import com.davidalmarinho.java2dgame.game_objects.components.Spritesheet;
import com.davidalmarinho.java2dgame.levels.Level;
import com.davidalmarinho.java2dgame.utils.WarningFrame;

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
    static Map<FileHandle, Level> levels = new HashMap<>();
    static Map<FileHandle, Texture> spritesheetMap = new HashMap<>();

    // Just checking if we already have a Sprite with the same location
    public static boolean hasSprite(String pictureFile) {
        return spriteMap.containsKey(pictureFile);
    }

    public static boolean hasLevel(FileHandle levelFile) {
        return levels.containsKey(levelFile);
    }

    /*public static TextureRegion getSprite(FileHandle fileHandle) {
        TextureRegion textureRegion =
        if (!hasSprite(pictureFile)) {
            // Add Sprite
            spriteMap.put(pictureFile, sprite);
        } else {
            sendWarning(pictureFile);
        }
        return sprite;
    }*/

    public static Texture getSpritesheet(FileHandle fileHandle) {
        Texture texture = new Texture(fileHandle);

        if (spritesheetMap.containsKey(fileHandle)) {
            // TODO: 18/04/2021 Make something when error ocurs
            new WarningFrame("");
        } else {
            spritesheetMap.put(fileHandle, texture);
        }
        return texture;
    }

    public static Level getLevel(FileHandle levelFile) {
        Level level = new Level(levelFile);
        if (!hasLevel(levelFile)) {
            levels.put(levelFile, level);
        } else {
            // sendWarning(levelFile);
            String warningMsg = "File already loaded: '" + levelFile.path() + "'";
        }
        return level;
    }

    // Will warning the developer if he adds the same file more than a time
    private static void sendWarning(String filePath) {
        // Throw a warning
        File file = new File(filePath);
        String warningMsg = "File already loaded: '" + file.getAbsolutePath() + "'";
        new WarningFrame(warningMsg);
        System.out.println(warningMsg);
    }
}
