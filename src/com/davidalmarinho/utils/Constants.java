package com.davidalmarinho.utils;

public class Constants {
    // Game/Screen
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int GAME_SCALE = 4;
    public static float WINDOW_SCALE_X = GAME_SCALE;
    public static float WINDOW_SCALE_Y = GAME_SCALE;

    // Levels
    public static final int TILE_SIZE = 16 * GAME_SCALE;
    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;

    // Gravity
    public static final float GRAVITY = 200;

    // LevelEditorScene Container
    public static final int X_CONTAINER = WINDOW_WIDTH / 2;
    public static final int Y_CONTAINER = WINDOW_HEIGHT / 2;
    public static final int BUTTON_WIDTH = 32;
    public static final int BUTTON_HEIGHT = 32;
    public static final int SPACE_BETWEEN_BUTTONS_HORIZONTAL = 16;
    public static final int SPACE_BETWEEN_BUTTONS_VERTICAL = 16;
}
