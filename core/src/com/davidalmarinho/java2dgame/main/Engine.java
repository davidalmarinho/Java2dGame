package com.davidalmarinho.java2dgame.main;

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
        gameLoop2();
    }

    /**
     * Based on Minecraft's Markus Persson (Notch) game loop
     */
    private void gameLoop1() {
        long lastRenderTime = System.nanoTime();
        // Limit fps that we can reach in the game
        double maxFps = 200.0;
        // Convert maxFps variable number to nano
        final float TIME_BETWEEN_RENDERS = (float) (1E9 / maxFps);
        float pcTime = .0f;
        // Show fps in terminal
        double lastTimeFPS = System.currentTimeMillis();
        int fps = 0;

        while (running) {
            long curTime = System.nanoTime();

            float delta = curTime - lastRenderTime;

            float convertedDelta = delta / TIME_BETWEEN_RENDERS;

            pcTime += convertedDelta;
            lastRenderTime = curTime;

            if (pcTime >= 1) {
                // Convert delta to seconds
                float deltaInSeconds = delta;
                if (deltaInSeconds < TIME_BETWEEN_RENDERS) {
                    deltaInSeconds = (float) (TIME_BETWEEN_RENDERS * 1E-9);
                } else {
                    deltaInSeconds = (float) (deltaInSeconds * 1E-9);
                }

                update(deltaInSeconds);
                fps++;
                pcTime--;
            }
            render();

            // Show fps in terminal
            /*if (System.currentTimeMillis() - lastTimeFPS >= 1000) {
                System.out.println(fps);
                fps = 0;
                lastTimeFPS += 1000;
            }*/
        }
        stop();
    }

    /**
     * My game loop :D
     */
    private void gameLoop2() {
        // Set limits
        float limitTickRatePerSecond = 120.0f;
        float maxFpsPerSecond = 1.0f / limitTickRatePerSecond;

        // float lastTime = Time.getTime();

        // Will keep all the laps of time
        float realTimePC = .0f;

        while (running) {
            /*float currentTime = Time.getTime();
            float frameTime = currentTime - lastTime;
<<<<<<< HEAD:core/src/com/davidalmarinho/java2dgame/main/Engine.java
            lastTime = currentTime;*/

            // We want real time to after limit update's time
            //realTimePC += frameTime;
=======
            lastTime = currentTime;

            // We want real time to after limit update's time
            realTimePC += frameTime;
>>>>>>> 9278d5149ed38a16740aade36fe01b1044f0381c:src/com/davidalmarinho/main/Engine.java

            // Limit time fps
            if (realTimePC >= maxFpsPerSecond) {
                // Breaker of counts
<<<<<<< HEAD:core/src/com/davidalmarinho/java2dgame/main/Engine.java
                /*if (frameTime <= maxFpsPerSecond) {
                    frameTime = maxFpsPerSecond;
                }
                update(frameTime);*/
=======
                if (frameTime <= maxFpsPerSecond) {
                    frameTime = maxFpsPerSecond;
                }
                update(frameTime);

                // Make the game sleeps
>>>>>>> 9278d5149ed38a16740aade36fe01b1044f0381c:src/com/davidalmarinho/main/Engine.java
                realTimePC -= maxFpsPerSecond;
            }
            render();

        }
        stop();
    }
}
