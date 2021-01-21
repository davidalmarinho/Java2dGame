package com.davidalmarinho;

import com.davidalmarinho.entity.Entity;
import com.davidalmarinho.entity.Player;
import com.davidalmarinho.graphics.Screen;
import com.davidalmarinho.graphics.SpriteSheet;
import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.world.World;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    // Renderer
    private final Window window;
    public static SpriteSheet spriteSheet;
    // Core
    private Thread thread;
    private boolean running;
    public static boolean errorOccurred;
    // Components
    private final Screen screen;
    private final Input input;
    public static World world;
    // Entities
    public static List<Entity> entities;
    public static Player player;
    // Controller
    private int curLevel = 0;

    public Game() {
        window = Window.get();
        input = new Input(this);
        screen = Screen.get();
        spriteSheet = new SpriteSheet("/spritesheet.png");
        entities = new ArrayList<>();
        world = new World(this, curLevel);
        player = new Player(20, 30, 16, 16, spriteSheet.getSprite(0, 0, 1, 1));
        entities.add(player);

        window.requestFocus();
    }

    private void tick() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
    }

    private void render() {
        screen.render();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;
    }

    @Override
    public void run() {
        this.gameLoop();
    }

    private void gameLoop() {
        long lastTime = System.nanoTime();
        float limitFps = 60.0f;
        double ns = 1000000000 / limitFps;
        double delta = 0;

        int fps = 0;
        double timer = System.currentTimeMillis();

        while (running) {
            long curTime = System.nanoTime();
            delta += (curTime - lastTime) / ns;
            lastTime = curTime;

            if (delta >= 1) {
                input.tick();
                this.tick();
                window.tick();

                fps++;
                delta--;
            }
            this.render();

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + fps);
                timer += 1000;
                fps = 0;
            }
        }
        this.stop();
    }

    public Window getWindow() {
        return window;
    }
}
