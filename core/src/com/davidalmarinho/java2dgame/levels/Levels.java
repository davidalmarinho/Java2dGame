package com.davidalmarinho.java2dgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.davidalmarinho.java2dgame.data_structures.AssetPool;

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

        // Check if last letter if a '/' and remove it if is to make our code more secure
        int lastCharIndex = levelsDirectoryPath.length() - 1;
        if (levelsDirectoryPath.charAt(lastCharIndex) == '/') {
            char[] old = levelsDirectoryPath.toCharArray();
            char[] updated = new char[levelsDirectoryPath.length() - 1];

            // We will copy each letter of levelsDirectoryPath String with exception of last '/' if it exists
            for (int currentChar = 0; currentChar < updated.length; currentChar++) {
                updated[currentChar] = old[currentChar];
            }
            levelsDirectoryPath = String.valueOf(updated);
            FileHandle levelsDir = Gdx.files.internal(levelsDirectoryPath);
            // System.out.println(levelsDirectoryPath);
        }

        // Check all the levels that our level's folder has
        while (true) {
            FileHandle fileHandle = Gdx.files.internal(levelsDirectoryPath + "/level" + currentLevel + ".png");
            if (fileHandle.exists()) {
                Level level = AssetPool.getLevel(fileHandle);
                levels.add(level);
                currentLevel++;
            } else {
                break;
            }
        }
    }

    // To add a level with security (using AssetPool) we always should use this method
    public void addLevel(String levelFile) {
        FileHandle fileHandle = Gdx.files.internal(levelFile);
        Level level = AssetPool.getLevel(fileHandle);
        levels.add(level);
    }

    public static Levels getInstance(String levelsDirectoryPath) {
        if (instance == null) {
            instance = new Levels(levelsDirectoryPath);
        }

        return instance;
    }
}
