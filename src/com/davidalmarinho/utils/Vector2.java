package com.davidalmarinho.utils;

public class Vector2 {
    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString() {
        return  "Position x: " + x + "\nPosition y: " + y;
    }
}
