package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.data_structures.AssetPool;

import java.util.ArrayList;
import java.util.List;

public class Spritesheet extends Component {
    public final List<Sprite> sprites;

    public Spritesheet(String path, int columns, int rows, int width, int height) {
        this.sprites = new ArrayList<>();

        Sprite parent = AssetPool.getSprite(path);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                sprites.add(new Sprite(parent.image.getSubimage(x * width, y * height, width, height)));
            }
        }
    }

    public Component copy() {
        return null;
    }
}
