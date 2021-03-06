package com.davidalmarinho.main;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();
        gameManager.init();
        gameManager.start();
    }
}
