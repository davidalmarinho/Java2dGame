package com.davidalmarinho.utils;

public class Time {
    private static final float lastTime = System.nanoTime();

    public static float getTime() {
        return (float) ((System.nanoTime() - lastTime) * 1E-9);
    }
}
