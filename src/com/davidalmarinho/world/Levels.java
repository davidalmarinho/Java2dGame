package com.davidalmarinho.world;

import com.davidalmarinho.data_structures.AssetPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Levels {
    private static Levels instance;
    public List<Level> levels;

    // Private and we instance it, because we want just a Levels manager
    private Levels(String levelsDirectoryPath) {
        levels = new ArrayList<>();
        int currentLevel = 0;

        // TODO 03/03/2021 Remove '/' from levelDirectoryPath if '/' is the last char

        // Check all the levels that our level's folder has
        while (true) {
            String fullPath = levelsDirectoryPath + "/level" + currentLevel + ".png";
            File file = new File(fullPath);
            if (file.exists()) {
                Level level = AssetPool.getLevel(fullPath);
                levels.add(level);
                currentLevel++;
            } else {
                break;
            }
        }
    }

    public static Levels getInstance(String levelsDirectoryPath) {
        if (instance == null) {
            instance = new Levels(levelsDirectoryPath);
        }

        return instance;
    }
}
