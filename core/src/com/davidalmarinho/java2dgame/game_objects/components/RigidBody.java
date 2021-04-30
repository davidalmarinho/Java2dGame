package com.davidalmarinho.java2dgame.game_objects.components;

import com.davidalmarinho.java2dgame.game_objects.GameObject;
import com.davidalmarinho.java2dgame.main.GameManager;
import com.davidalmarinho.java2dgame.utils.Constants;
import com.davidalmarinho.java2dgame.utils.Vector2;

public class RigidBody extends Component {
    public Vector2 velocity;
    protected boolean jumping;
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
    }

    /**
     * Check if the next pixel if free
     *
     * @param xNext The next x's gameObject position
     * @param yNext The next y's gameObject position
     * @return A solid collision between these 2 gameObjects
     */
    public  <T extends Component> boolean isFree(Class<T> component, float xNext, float yNext) {
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

    @Override
    public Component copy() {
        return new RigidBody(this.velocity.copy());
    }
}
