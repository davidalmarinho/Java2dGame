package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.GameManager;
import com.davidalmarinho.input.KeyboardInput;
import com.davidalmarinho.utils.Vector2;

import java.awt.event.KeyEvent;

public class RigidBody extends Component {
    public Vector2 velocity;

    public RigidBody(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(float dt) {
        // If player component as been found, we will move the game object Player
        if (gameObject.getComponent(Player.class) != null) {
            controlPlayer(dt);
        }
    }

    private void controlPlayer(float dt) {
        KeyboardInput keyboardInput = GameManager.getInstance().getKeyboardInput();
        float x = gameObject.transform.position.x;
        float y = gameObject.transform.position.y;
        if (keyboardInput.isKey(KeyEvent.VK_W) || keyboardInput.isKey(KeyEvent.VK_UP)) {
            // Moves a certain number of pixels (velocity.x, velocity.y) per second
            y -= dt * velocity.y;
        } else if (keyboardInput.isKey(KeyEvent.VK_S) || keyboardInput.isKey(KeyEvent.VK_DOWN)) {
            y += dt * velocity.y;
        }

        if (keyboardInput.isKey(KeyEvent.VK_D) || keyboardInput.isKey(KeyEvent.VK_RIGHT)) {
            x += dt * velocity.x;
        } else if (keyboardInput.isKey(KeyEvent.VK_A) || keyboardInput.isKey(KeyEvent.VK_LEFT)) {
            x -= dt * velocity.x;
        }
        gameObject.transform.position.x = x;
        gameObject.transform.position.y = y;
    }
}
