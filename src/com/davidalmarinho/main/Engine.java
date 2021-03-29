package com.davidalmarinho.main;

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

    public abstract void init();

    public abstract void update(float dt);

    public abstract void render();

    @Override
    public void run() {
        float limitTickRatePerSecond = 120.0f;
        float maxFpsPerSecond = 1.0f / limitTickRatePerSecond;

        float lastTime = Time.getTime();

        float realTimePC = .0f;

        while (running) {
            float currentTime = Time.getTime();
            float frameTime = currentTime - lastTime;
            lastTime = currentTime;


            // We want real time to after limit update's time
            realTimePC += frameTime;

            // Breaker of counts
            if (frameTime < maxFpsPerSecond) {
                frameTime = maxFpsPerSecond;
            }

            // Limit time fps
            if (realTimePC >= maxFpsPerSecond) {
                update(frameTime);
                realTimePC -= maxFpsPerSecond;
            }

            render();
        }
        stop();
    }
}
