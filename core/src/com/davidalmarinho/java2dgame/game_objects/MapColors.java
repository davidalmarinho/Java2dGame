package com.davidalmarinho.java2dgame.game_objects;

import com.davidalmarinho.java2dgame.utils.Color;

public enum MapColors {
    PLAYER_COLOR(new Color.BGRA(0, 0, 255, 255).parseDecimal()),
    WALL_COLOR(new Color.BGRA(0, 0, 0, 255).parseDecimal()),
    FLOOR_COLOR(new Color.BGRA(255, 255, 255, 255).parseDecimal());

    private final int color;

    MapColors(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
