package com.davidalmarinho.java2dgame.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.davidalmarinho.java2dgame.data_structures.Transform;
import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.game_objects.components.BoxBounds;
import com.davidalmarinho.java2dgame.game_objects.components.Player;
import com.davidalmarinho.java2dgame.game_objects.components.RigidBody;
import com.davidalmarinho.java2dgame.game_objects.components.Spritesheet;
import com.davidalmarinho.java2dgame.levels.Levels;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.Vector2;

public class LevelScene extends Scene {
   public Spritesheet spritesheet;
   private Levels levels;
   int level = 0;
   public GameObject player;
   private float parseLevelFpsTest;
   private boolean firstTimeParseLevelTest = true;

    @Override
    public void init() {
        this.spritesheet = new Spritesheet("spritesheet.png", 4, 6,
                16, 16);
        player = new GameObject("Player", new Transform(new Vector2(200, 200)));
        Player playerComp = new Player(spritesheet.getSprites().get(8));
        player.addComponent(playerComp);
        player.addComponent(new RigidBody(new Vector2(200.0f, 200.0f)));
        player.addComponent(new BoxBounds(Constants.TILE_SIZE, Constants.TILE_SIZE));
        player.depth = 2;
        addGameObject(player);
        initLevel();
    }

    private void initLevel() {
        this.levels = Levels.getInstance("levels/");
        Levels.getInstance("levels/").levels.get(level).init();
    }

    @Override
    public void update(float dt) {
        updateGameObjects(dt);
        camera.lock(player);
        // Just to check if the levels management is going well (3 seconds and it switches to the 2nd level
        /*parseLevelFpsTest += dt;
        if (parseLevelFpsTest >= 3.0 && firstTimeParseLevelTest) {
            level++;
            Levels.getInstance("assets/levels").levels.get(level).init();
            parseLevelFpsTest = 0;
            firstTimeParseLevelTest = false;
        }*/
    }

    private void test() {
        // setScreen();
        //new ErrorFrame("Error test");
        // spritesheet = new Texture("assets/spritesheet.png");
        levels = Levels.getInstance("assets/levels/");

        // Creating a spritesheet with the same location of the spritesheet above to check if AssetPool is working fine
        // new Spritesheet("assets/spritesheet.png", 4, 6, 16, 16);
        Levels.getInstance("assets/levels").levels.get(level).init();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.draw(GameManager.getInstance().getBatch());

        /*SpriteBatch batch = gameManager.getBatch();
        batch.begin();
        batch.draw(spritesheet.sprites.get(0), 0, 0,
                Constants.TILE_SIZE, Constants.TILE_SIZE);
        batch.end();*/
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
