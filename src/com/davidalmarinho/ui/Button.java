package com.davidalmarinho.ui;

import com.davidalmarinho.data_structures.AssetPool;
import com.davidalmarinho.data_structures.Transform;
import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.*;
import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.scenes.LevelEditorScene;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class Button extends Component {
    private final ItemsTypes buttonType;
    private final Vector2 position;
    private final Sprite sprite;
    public boolean selected;

    public Button(ItemsTypes buttonType, Sprite sprite, Vector2 position) {
        this.buttonType = buttonType;
        this.position = position;
        this.sprite = sprite;
    }

    @Override
    public void update(float dt) {
        MouseInput mouseInput = GameManager.getInstance().mouseInput;
        // Checking if the mouse is above of the button
        boolean aboveButton = mouseInput.mousePosition.x > position.x
                && mouseInput.mousePosition.y > position.y
                && mouseInput.mousePosition.x < position.x + Constants.BUTTON_WIDTH
                && mouseInput.mousePosition.y < position.y + Constants.BUTTON_HEIGHT;

        if (mouseInput.isButtonDown(MouseEvent.BUTTON1) && aboveButton) {
            gameObject = new GameObject("Selected", new Transform(new Vector2()));
            gameObject.removeComponent(Button.class);
            gameObject.addComponent(((LevelEditorScene) GameManager.getInstance().getCurrentScene())
                    .mouse.getComponent(SnapToGrid.class));
            gameObject.addComponent(sprite);

            LevelEditorScene levelScene = (LevelEditorScene) GameManager.getInstance().getCurrentScene();
            if (buttonType == ItemsTypes.WALL) {
                gameObject = Constants.createWall(position.copy(),
                        levelScene.editorSpritesheet.sprites.get(3));

            } else if (buttonType == ItemsTypes.FLOOR) {
                gameObject = Constants.createFloor(position.copy(),
                        levelScene.editorSpritesheet.sprites.get(2));
            }
            this.gameObject = gameObject.copy();
            selected = true;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToIdentity();
        affineTransform.translate(position.x, position.y);
        affineTransform.scale(2, 2);
        g2.drawImage(sprite.image, affineTransform, null);
    }

    @Override
    public Component copy() {
        return new Button(this.buttonType, this.sprite, this.position.copy());
    }
}
