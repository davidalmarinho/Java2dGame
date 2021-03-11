package com.davidalmarinho.scenes;

import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.Grid;
import com.davidalmarinho.game_objects.components.SnapToGrid;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.*;

public class LevelEditorScene extends Scene {

    @Override
    public void init() {
        GameObject editor = new GameObject("Editor", new Transform(new Vector2()));
        editor.addComponent(new Grid());
        editor.addComponent(new SnapToGrid());
        addGameObject(editor);
    }

    @Override
    public void update(float dt) {
        updateGameObjects(dt);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        renderer.draw(g2);
    }
}
