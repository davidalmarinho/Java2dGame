package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.davidalmarinho.java2dgame.data_structures.AssetPool;

import java.util.ArrayList;
import java.util.List;

public class Spritesheet {
    public final List<Sprite> sprites;

    public Spritesheet(String path, int columns, int rows, int width, int height) {
        this.sprites = new ArrayList<>();

        FileHandle fileHandle = Gdx.files.internal(path);

        Texture parent = AssetPool.getSpritesheet(fileHandle);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                sprites.add(new Sprite(new TextureRegion(parent, x * width, y * height,
                        width, height)));
            }
        }
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public Component copy() {
        return null;
    }
}
