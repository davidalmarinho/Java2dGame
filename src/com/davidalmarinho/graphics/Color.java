package com.davidalmarinho.graphics;

public class Color {
    public int r, g, b, a;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getColor() {
        /* >> -> var x / 2 ^ number
         * << -> var x * 2 ^ number
         * */
        if (a == 0) {
            String hexadecimalColor = getHexadecimalColor(r) + getHexadecimalColor(g) + getHexadecimalColor(b);
            return convertIntoDecimalColor(hexadecimalColor);
        } else {
            String hexadecimalRGBColor = getHexadecimalColor(r) + getHexadecimalColor(g) +
                    getHexadecimalColor(b);
            int rgbColor = convertIntoDecimalColor(hexadecimalRGBColor);

            String hexadecimalARGBColor = getHexadecimalColor(a);
            // System.out.println(getHexadecimalColor(a));
            // System.out.println(hexadecimalRGBColor);
            int opc = getOpacity(hexadecimalARGBColor);
            // System.out.println(rgbColor + opc);
            // System.out.println(0x3cff0000);
            return rgbColor + opc;
        }
    }

    private String getHexadecimalColor(int color) {
        // Convert RGB to Hexadecimal
        /* Ex.:
         * color = 255;
         * color / 16 = 15.9375;
         * firstLetter = 15;
         * secondLetter = 0,9375 * 16 = 15;
         * */
        int findFirstHexDigit = color >> 4;
        double findSecondHexDigit = (((double) color / 16) - findFirstHexDigit) * 16;

        String firstLetter = getLetterFromTable(findFirstHexDigit);
        String secondLetter = getLetterFromTable((int) findSecondHexDigit);

        return firstLetter + secondLetter; // 1687565055
    }

    private int getOpacity(String hexARGColor) {
        char[] hexArgb = hexARGColor.toCharArray();
        int letter0 = 0;
        int letter1 = 0;

        for (int i = 0; i < hexArgb.length; i++) {
            if (i == 0) {
                int hexValue = getHexNumberFromTable(hexArgb[0]);

                // Check if we have to negate the number due to it may be a very high number
                /*if (hexValue * 268435440 >= Integer.MAX_VALUE) { // 16 * 16777215

                    letter0 = -hexValue * 268435456; // 16 * 16777216
                    if (hexArgb[i] == 'f')
                        breaker = 224;
                    else if (hexArgb[i] == 'e')
                        breaker = 192;
                    else if (hexArgb[i] == 'd')
                        breaker = 160;
                    else if (hexArgb[i] == 'c')
                        breaker = 128;
                    else if (hexArgb[i] == 'b')
                        breaker = 96;
                    else if (hexArgb[i] == 'a')
                        breaker = 64;
                    else if (Integer.parseInt(String.valueOf(hexArgb[i])) == 9)
                        breaker = 16;

                } else {
                }*/

                letter0 = hexValue * 268435456;
                // letter0 += 0;

            } else {
                int hexValue = getHexNumberFromTable(hexArgb[1]);
                letter1 = hexValue * 16777216;
            }
        }
        return letter0 + letter1;
    }

    private int convertIntoDecimalColor(String hexColor) {
        char[] hexArray = hexColor.toCharArray();
        int color = 0;
        for (int i = hexArray.length - 1; i >= 0; i--) {
            int val;
            int curIndex = hexArray.length - 1 - i;

            val = getHexNumberFromTable(hexArray[i]);

            color += val * (Math.pow(16, curIndex));
        }

        return color;
    }

    private String getLetterFromTable(int rgb) {
        if (rgb == HexadecimalColor.numberF()) {
            return HexadecimalColor.F();
        } else if (rgb == HexadecimalColor.numberE()) {
            return HexadecimalColor.E();
        } else if (rgb == HexadecimalColor.numberD()) {
            return HexadecimalColor.D();
        } else if (rgb == HexadecimalColor.numberC()) {
            return HexadecimalColor.C();
        } else if (rgb == HexadecimalColor.numberB()) {
            return HexadecimalColor.B();
        } else if (rgb == HexadecimalColor.numberA()) {
            return HexadecimalColor.A();
        } else {
            return String.valueOf(rgb);
        }
    }

    private int getHexNumberFromTable(char current) {
        if (current == 'f') {
            return HexadecimalColor.numberF();
        } else if (current == 'e') {
            return HexadecimalColor.numberE();
        } else if (current == 'd') {
            return HexadecimalColor.numberD();
        } else if (current == 'c') {
            return HexadecimalColor.numberC();
        } else if (current == 'b') {
            return HexadecimalColor.numberB();
        } else if (current == 'a') {
            return HexadecimalColor.numberA();
        } else {
            return Integer.parseInt(String.valueOf(current));
        }
    }

    public static class HexadecimalColor {
        public static String A() {
            return "a";
        }

        public static int numberA() {
            return 10;
        }

        public static String B() {
            return "b";
        }

        public static int numberB() {
            return 11;
        }

        public static String C() {
            return "c";
        }

        public static int numberC() {
            return 12;
        }

        public static String D() {
            return "d";
        }

        public static int numberD() {
            return 13;
        }

        public static String E() {
            return "e";
        }

        public static int numberE() {
            return 14;
        }

        public static String F() {
            return "f";
        }

        public static int numberF() {
            return 15;
        }
    }
}
