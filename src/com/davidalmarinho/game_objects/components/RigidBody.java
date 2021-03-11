package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.input.KeyboardInput;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.event.KeyEvent;

public class RigidBody extends Component {
    public Vector2 velocity;
    private boolean jumping;
    private float jump;

    public RigidBody(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(float dt) {
        // This code will be to a special mode in our game :)))
        /*float gravity = dt * velocity.y;
        // If we aren't jumping, we are falling, so we will add the gravity to this place
        if (!jumping) {
            for (float speed = gravity; speed >= 0; speed -= 1.0f) {
                // Check floor collision
                if (isFree(Wall.class, gameObject.transform.position.x, gameObject.transform.position.y + speed)) {
                    gameObject.transform.position.y += speed;
                }
            }
        }

        // If we are jumping, we will jump during a certain period of time
        if (jumping) {
            for (float speed = gravity; speed >= 0; speed -= 1.0f) {
                // Check "roof" collision
                if (isFree(Wall.class, gameObject.transform.position.x, gameObject.transform.position.y - speed)) {
                    gameObject.transform.position.y -= speed;
                }
            }
            jump += dt;
            if (jump >= 6.0f) {
                jump = .0f;
                jumping = false;
            }
        }*/

        // If player component as been found, we will move the game object Player
        if (gameObject.getComponent(Player.class) != null) {
            controlPlayer(dt);
        }
    }

    private void controlPlayer(float dt) {
        KeyboardInput keyboardInput = GameManager.getInstance().getKeyboardInput();
        Vector2 playerCoordinates = new Vector2(gameObject.transform.position.x, gameObject.transform.position.y);

        for (float speed = velocity.x * dt; speed >= 0; speed -= 1.0f) {
            if (keyboardInput.isKey(KeyEvent.VK_D)
                    && isFree(Wall.class, playerCoordinates.x + speed, playerCoordinates.y)) {
                // Moves a certain number of pixels (velocity.x, velocity.y) per second
                playerCoordinates.x += speed;
            } else if (keyboardInput.isKey(KeyEvent.VK_A)
                    && isFree(Wall.class, playerCoordinates.x - speed, playerCoordinates.y)) {
                playerCoordinates.x -= speed;
            }
        }

        if (keyboardInput.isKeyDown(KeyEvent.VK_SPACE)
                && !isFree(Wall.class, playerCoordinates.x, playerCoordinates.y + 2)) {
            jumping = true;
        }

        for (float speed = velocity.y * dt; speed >= 0; speed -= 1.0f) {
            if (keyboardInput.isKey(KeyEvent.VK_W)) {
                if (isFree(Wall.class, playerCoordinates.x, playerCoordinates.y - speed)) {
                    playerCoordinates.y -= speed;

                }
            } else if (keyboardInput.isKey(KeyEvent.VK_S)) {
                if (isFree(Wall.class, playerCoordinates.x, playerCoordinates.y + speed)) {
                    playerCoordinates.y += speed;
                }
            }
        }

        gameObject.transform.position = playerCoordinates;
    }

    /**
     * To check if a gameObject is colliding with another gameObject without solid collision
     *
     * @param gameObject1 The gameObject that is colliding with
     * @return If these gameObjects are colliding
     */
    private boolean isColliding(GameObject gameObject1) {
        for (GameObject gameObject : GameManager.getInstance().getCurrentScene().gameObjects) {
            if (gameObject.getComponent(Wall.class) != null) {
                if (gameObject.transform.position.x + 1 + gameObject.getComponent(BoxBounds.class).width >= gameObject1.transform.position.x &&
                        gameObject1.transform.position.x + gameObject1.getComponent(BoxBounds.class).width >= gameObject.transform.position.x - 1 &&
                        gameObject.transform.position.y + 1 + gameObject.getComponent(BoxBounds.class).height >= gameObject1.transform.position.y &&
                        gameObject1.transform.position.y + gameObject1.getComponent(BoxBounds.class).height >= gameObject.transform.position.y - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the next pixel if free
     *
     * @param xNext The next x's gameObject position
     * @param yNext The next y's gameObject position
     * @return A solid collision between these 2 gameObjects
     */
    private <T extends Component> boolean isFree(Class<T> component, float xNext, float yNext) {
        for (GameObject currentGameObject : GameManager.getInstance().getCurrentScene().gameObjects) {
            if (currentGameObject.getComponent(component) != null) {
                // GameObject's sizes
                BoxBounds boxBounds = gameObject.getComponent(BoxBounds.class);
                int width = boxBounds.width;
                int height = boxBounds.height;

                /* Requirements for loop:
                 *
                 *      Pick these positions
                 *      ↓                ↓
                 *      .________________.
                 *      |                |
                 *      |                |
                 *      |                |
                 *      |                |
                 *      |________________|
                 *      ↑                ↑
                 *    And these positions too
                 */
                int startX = (int) (xNext / Constants.TILE_SIZE);
                int startY = (int) (yNext / Constants.TILE_SIZE);
                int finalX = (int) Math.ceil(xNext + width - 1) / Constants.TILE_SIZE;
                int finalY = (int) Math.ceil(yNext + height - 1) / Constants.TILE_SIZE;

                // Save coordinates of solid gameObject
                Vector2 solidCoordinates = new Vector2(
                        currentGameObject.transform.position.x / Constants.TILE_SIZE,
                        currentGameObject.transform.position.y / Constants.TILE_SIZE);

                // Using loop to, if we have bigger squared gameObjects, it works fine
                for (int x = startX; x <= finalX; x++) {
                    for (int y = startY; y <= finalY; y++) {
                        if (x == (int) (solidCoordinates.x) && y == (int) (solidCoordinates.y)) {
                            return false;
                        }
                    }
                }
            }
        }

        // So far so good, we are not colliding
        return true;
    }
}
