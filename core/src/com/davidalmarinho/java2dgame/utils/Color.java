package com.davidalmarinho.java2dgame.utils;

public abstract class Color {
    protected int alpha, red, green, blue;

    // Will use RGB format for default
    public Color(int r, int g, int b) {
        this.alpha = 255;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public abstract int parseDecimal();

    public static class BGRA extends Color {
        public BGRA(int b, int g, int r, int a) {
            super(r, g, b);
            this.alpha = a;
        }

        public BGRA(int b, int g, int r) {
            super(r, g, b);
            this.alpha = 255;
        }

        @Override
        public int parseDecimal() {
            int alpha = this.alpha;
            int blue = this.blue << 8;
            int green = this.green << 16;
            int red = this.red << 24;
            return alpha + blue + green + red;
        }
    }

    public static class ARGB extends Color {

        public ARGB(int a, int r, int g, int b) {
            super(r, g, b);
            this.alpha = a;
        }

        public ARGB(int r, int g, int b) {
            super(r, g, b);
            this.alpha = 255;
        }

        @Override
        public int parseDecimal() {
            int alpha = this.alpha << 24;
            int red = this.red << 16;
            int green = this.green << 8;
            int blue = this.blue;
            return blue + green + red + alpha;
        }
    }
}
