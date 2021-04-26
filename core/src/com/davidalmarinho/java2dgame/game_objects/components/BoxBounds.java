package com.davidalmarinho.java2dgame.game_objects.components;

public class BoxBounds extends Component {
    public final int width, height;

    public BoxBounds(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Component copy() {
        return new BoxBounds(this.width, this.height);
    }
}
