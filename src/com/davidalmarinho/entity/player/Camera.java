package com.davidalmarinho.entity.player;

public class Camera {
    public static int x, y;

    public static int clamp(int current, int min, int max) {
        if (current < min) {
            current = min;
        }
        if (current > max) {
            current = max;
        }
        return current;
    }
}
