package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.utils.Vector2;

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
        Vector2 playerCoordinates = new Vector2(gameObject.transform.position.x, gameObject.transform.position.y);
        Vector2 velocity = rigidBody.velocity;

        for (float speed = velocity.x * dt; speed >= 0; speed -= 1.0f) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)
                    && rigidBody.isFree(Wall.class, playerCoordinates.x + speed, playerCoordinates.y)) {
                // Moves a certain number of pixels (velocity.x, velocity.y) per second
                playerCoordinates.x += (int) speed;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)
                    && rigidBody.isFree(Wall.class, playerCoordinates.x - speed, playerCoordinates.y)) {
                playerCoordinates.x -= (int) speed;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)
                && !rigidBody.isFree(Wall.class, playerCoordinates.x, playerCoordinates.y + 2)) {
            rigidBody.jumping = true;
        }

        for (float speed = velocity.y * dt; speed >= 0; speed -= 1.0f) {
            if (Gdx.input.isKeyPressed(Input.Keys.S) && rigidBody.isFree(Wall.class,
                    playerCoordinates.x, playerCoordinates.y - speed)) {
                playerCoordinates.y -= (int) speed;
            } else if (Gdx.input.isKeyPressed(Input.Keys.W) && rigidBody.isFree(Wall.class,
                    playerCoordinates.x, playerCoordinates.y + speed)) {
                playerCoordinates.y += (int) speed;
            }
        }

        gameObject.transform.position = playerCoordinates;
    }

    @Override
    public void render(SpriteBatch batch) {
        drawQuick(batch, sprite);
    }

    @Override
    public Component copy() {
        return new Player(this.sprite);
    }
}
