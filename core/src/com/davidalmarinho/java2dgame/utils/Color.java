package com.davidalmarinho.java2dgame.utils;

public class Color {
    private final int opacity, red, green ,blue;

    public Color(int a, int r, int g, int b) {
        this.opacity = a;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public Color(int r, int g, int b) {
        this.opacity = 255;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public int parseDecimal() {
        int opacity = this.opacity << 24;
        int red = this.red << 16;
        int green = this.green << 8;
        int blue = this.blue;

        return opacity + red + green + blue;
    }
}
