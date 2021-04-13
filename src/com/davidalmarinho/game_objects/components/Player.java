package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.input.KeyboardInput;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Component {
    private final Sprite sprite;

    public Player(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(float dt) {
        // If RigidBody's component as been found, we will move the game object Player
        RigidBody playerRigidBody = gameObject.getComponent(RigidBody.class);
        if (playerRigidBody != null) {
            movePlayer(dt, playerRigidBody);
        }
    }

    private void movePlayer(float dt, RigidBody rigidBody) {
        KeyboardInput keyboardInput = GameManager.getInstance().getKeyboardInput();
        Vector2 playerCoordinates = new Vector2(gameObject.transform.position.x, gameObject.transform.position.y);
        Vector2 velocity = rigidBody.velocity;

        for (float speed = velocity.x * dt; speed >= 0; speed -= 1.0f) {
            if (keyboardInput.isKey(KeyEvent.VK_D)
                    && rigidBody.isFree(Wall.class, playerCoordinates.x + speed, playerCoordinates.y)) {
                // Moves a certain number of pixels (velocity.x, velocity.y) per second
                playerCoordinates.x += speed;
            } else if (keyboardInput.isKey(KeyEvent.VK_A)
                    && rigidBody.isFree(Wall.class, playerCoordinates.x - speed, playerCoordinates.y)) {
                playerCoordinates.x -= speed;
            }
        }

        /*if (keyboardInput.isKeyDown(KeyEvent.VK_SPACE)
                && !rigidBody.isFree(Wall.class, playerCoordinates.x, playerCoordinates.y + 2)) {
            rigidBody.jumping = true;
        }*/

        for (float speed = velocity.y * dt; speed >= 0; speed -= 1.0f) {
            if (keyboardInput.isKey(KeyEvent.VK_W) && rigidBody.isFree(Wall.class,
                    playerCoordinates.x, playerCoordinates.y - speed)) {
                playerCoordinates.y -= speed;
            } else if (keyboardInput.isKey(KeyEvent.VK_S) && rigidBody.isFree(Wall.class,
                    playerCoordinates.x, playerCoordinates.y + speed)) {
                playerCoordinates.y += speed;
            }
        }

        gameObject.transform.position = playerCoordinates;
    }

    @Override
    public void render(Graphics2D g2) {
        drawQuick(g2, sprite);
    }

    @Override
    public Component copy() {
        return new Player(this.sprite);
    }
}
