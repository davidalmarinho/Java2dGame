package com.davidalmarinho.utils;

public class Transform {
    public Vector2 position;
    public Vector2 scale;
    public float rotation;

    public Transform(Vector2 position) {
        this.position = position;
        this.scale = new Vector2(1, 1);
        this.rotation = .0f;
    }
}
