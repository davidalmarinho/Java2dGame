package com.davidalmarinho;

import com.davidalmarinho.utils.Time;

public abstract class Engine implements Runnable {
    // Core
    private boolean running;
    private Thread thread;

    // Starts the main Thread to start game
    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Error: Can't stop game's thread");
            e.printStackTrace();
        }
    }

    public abstract void update(float dt);
    public abstract void render();

    @Override
    public void run() {
        // TODO It's a little buggy, fix this
        float lastTime = .0f;

        try {
            while (running) {
                float currentTime = Time.getTime();
                float delta = currentTime - lastTime;
                lastTime = currentTime;

                update(delta);
                render();
            }
            stop();
        } catch (Exception e) {
            System.out.println("Error: GameLooping crashed");
            e.printStackTrace();
        }
    }
}
