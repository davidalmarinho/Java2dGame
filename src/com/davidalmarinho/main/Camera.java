package com.davidalmarinho.main;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.game_objects.components.BoxBounds;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

public class Camera {
    private static Camera instance;
    public Vector2 position;

    private Camera() {
        position = new Vector2();
    }

    private float clamp(float current, float min, float max) {
        if (current <= min) {
            current = min;
        }

        if (current >= max) {
            current = max;
        }

        return current;
    }

    /**
     * Updates the Camera
     *
     * @param gameObject The camera position will lock in this gameObject
     */
    public void lockCamera(GameObject gameObject) {
        BoxBounds boxBoundsGameObject = gameObject.getComponent(BoxBounds.class);

        Vector2 cameraCenterPosition = new Vector2(
                gameObject.transform.position.x - (float) (Constants.WINDOW_WIDTH / 2)
                        + (float) boxBoundsGameObject.width / 2,
                gameObject.transform.position.y - (float) (Constants.WINDOW_HEIGHT / 2)
                        + (float) boxBoundsGameObject.height / 2);
        Vector2 cameraEndPosition = new Vector2(
                Constants.WORLD_WIDTH - Constants.WINDOW_WIDTH,
                Constants.WORLD_HEIGHT - Constants.WINDOW_HEIGHT);

        this.position.x = clamp(cameraCenterPosition.x, 0, cameraEndPosition.x);
        this.position.y = clamp(cameraCenterPosition.y, 0, cameraEndPosition.y);
    }

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }
}
