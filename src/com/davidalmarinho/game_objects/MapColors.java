package com.davidalmarinho.game_objects;

public enum MapColors {
    PLAYER_COLOR(0xFFff0000), WALL_COLOR(0xFF000000), FLOOR_COLOR(0xFFffffff);

    private final int color;

    MapColors(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
