package com.davidalmarinho.java2dgame.game_objects;

import com.davidalmarinho.java2dgame.utils.Color;

public enum MapColors {
    PLAYER_COLOR(new Color(255, 255, 0, 0).parseDecimal()),
    WALL_COLOR(new Color(255, 0, 0, 0).parseDecimal()),
    FLOOR_COLOR(new Color(255, 255, 255, 255).parseDecimal());

    private final int color;

    MapColors(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
