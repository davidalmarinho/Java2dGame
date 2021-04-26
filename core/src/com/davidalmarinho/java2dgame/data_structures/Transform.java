package com.davidalmarinho.java2dgame.data_structures;

import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.Vector2;

public class Transform {
    public Vector2 position;
    public Vector2 scale;
    public float rotation;

    // Save the position, scale and the rotation of the game object
    public Transform(Vector2 position) {
        this.position = position;
        this.scale = new Vector2(Constants.GAME_SCALE, Constants.GAME_SCALE);
        this.rotation = .0f;
    }

    public Transform copy() {
        return new Transform(this.position.copy());
    }
}
